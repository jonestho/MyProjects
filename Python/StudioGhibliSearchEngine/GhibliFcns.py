import GhibliObjects
import json
import requests


def jprint(obj):
    text = json.dumps(obj, sort_keys=True, indent=4)
    print(text)


def movieSearch(url):
    parameters = {
        'fields': '',
        'limit': 250,
    }

    response = requests.get(url, params=parameters)
    print('Retrieving data...\n')

    movies = response.json()

    year_input = input('What year range for the movies? (Enter both years separated by a space): ')
    year_split = year_input.split()
    year_range = []

    for year in year_split:
        year_range.append(int(year))

    # Selects movies that were made during the year range given.
    selected_movies = []
    for i in range(len(movies)):
        if year_range[0] <= int(movies[i]['release_date']) <= year_range[1]:
            selected_movies.append(movies[i])

    # These lists contain data that will be used for the attributes in the objects.
    movie_titles = []
    descriptions = []
    release_dates = []
    directors = []
    rt_scores = []

    # Each of the attributes will correspond with each other by index. This is done
    # so when we're creating an object, all the attributes will match each other.

    for movie in selected_movies:
        movie_titles.append(movie['title'])
        descriptions.append(movie['description'])
        release_dates.append(movie['release_date'])
        directors.append(movie['director'])
        rt_scores.append(movie['rt_score'])

    movie_list = []  # Initiating the objects list (i.e., the list containing movies).

    # Appending three objects to our list
    for i in range(0, 3):
        movie = GhibliObjects.Movie(movie_titles[i], descriptions[i], release_dates[i], directors[i], rt_scores[i])
        print(movie.__str__())
        movie.update_score(int(input('What score would you give this movie?: ')))
        movie_list.append(movie)

    for movie in movie_list:
        print(movie.__str__())

    print('Your rated movies are listed above.\n')

    while True:
        file_prompt = input('Write to a file, "StudioGhibliFilms.txt"? (Y/N): ')
        if file_prompt == 'Y':
            with open('StudioGhibliFilms.txt', 'w', encoding='utf-8') as file:
                for movie in movie_list:
                    file.write(movie.__str__())
            print('\nSuccess! Take a look at the file.\n')
            break
        elif file_prompt == 'N':
            break
        else:
            print('Invalid prompt. Try again\n')


def peopleSearch(url):
    parameters = {
        'fields': '',
        'limit': 250,
    }

    response = requests.get(url, params=parameters)
    print('Retrieving data...')

    people = response.json()

    # Raw film ids
    id_list = []
    for i in range(len(people)):
        film_ids = people[i]['films']
        for film_id in film_ids:
            id_list.append(film_id)

    # Removing duplicates in the raw list
    id_list = list(set(id_list))

    # Getting the movie title from each link (ID)
    film_names = []
    for film_id in id_list:
        response = requests.get(film_id, params=parameters)
        film_page = response.json()
        title = film_page['title']
        film_names.append(title)

    # This dictionary will contain the movie title as each key, and the movie id as its value.
    films = {}
    for i in range(len(id_list)):
        films.update({film_names[i]: id_list[i]})

    # All the films in the API that have info on characters.
    i = 1
    for film in films:
        print('({}) {}'.format(i, film))
        i += 1

    selected_film = input('\n(Pick any film listed above): \n')
    print('Retrieving data...')

    characters = []
    gender = []
    age = []
    hair_color = []
    species = []
    selected_id = films[selected_film]

    # Gathering the attributes for our person
    for i in range(len(people)):
        person = people[i]['name']
        if 'gender' not in people[i]:
            sex = people[i]['gander']
        else:
            sex = people[i]['gender']
        age_years = people[i]['age']
        hair = people[i]['hair_color']
        race = people[i]['species']
        response = requests.get(race, params=parameters)
        race_result = response.json()

        if isinstance(race_result, dict):
            race = race_result['name']
        else:
            race = race_result[0]['name']

        film_ids = people[i]['films']
        for film in film_ids:
            if selected_id == film:
                characters.append(person)
                gender.append(sex)
                hair_color.append(hair)
                age.append(age_years)
                species.append(race)

    # Listing the characters in our selected movie
    print('\n')
    for i in range(len(characters)):
        print('({}) {}'.format(i + 1, characters[i]))
    print('\n')

    char_num = int(input('Which Character would you like to learn about? (Type any number listed above):\n'))

    character = GhibliObjects.Character(characters[char_num - 1], selected_film, gender[char_num - 1],
                                        age[char_num - 1],
                                        hair_color[char_num - 1], species[char_num - 1])
    print('\n')
    print(character.__str__(), '\n')


def locationSearch(url):
    parameters = {
        'fields': '',
        'limit': 250,
    }

    response = requests.get(url, params=parameters)
    print('Retrieving data...')

    locations = response.json()

    id_list = []
    for i in range(len(locations)):
        film_ids = locations[i]['films']
        for film_id in film_ids:
            id_list.append(film_id)

    id_list = list(set(id_list))

    film_names = []
    for film_id in id_list:
        response = requests.get(film_id, params=parameters)
        film_page = response.json()
        title = film_page['title']
        film_names.append(title)

    films = {}
    for i in range(len(id_list)):
        films.update({film_names[i]: id_list[i]})

    # All the films in the API that have a location specified.
    i = 1
    for film in films:
        print('({}) {}'.format(i, film))
        i += 1

    selected_film = input('\n(Pick any film listed above):\n')
    print('Retrieving data')
    selected_id = films[selected_film]

    loc_list = []
    climates = []
    terrains = []
    srfc_waters = []
    loc_residents = []

    for i in range(len(locations)):
        location = locations[i]['name']
        climate = locations[i]['climate']
        terrain = locations[i]['terrain']
        srfc_water = locations[i]['surface_water']
        residents = locations[i]['residents']

        film_ids = locations[i]['films']
        for film in film_ids:
            if selected_id == film:
                loc_list.append(location)
                climates.append(climate)
                terrains.append(terrain)
                srfc_waters.append(srfc_water)
                loc_residents.append(residents)

    print('\n')
    for i in range(len(loc_list)):
        print('({}) {}'.format(i + 1, loc_list[i]))
    print('\n')

    loc_num = int(input('Which location would you like to learn about?\n(Pick the number associated with the location):'
                        '\n'))

    location = GhibliObjects.Location(loc_list[loc_num-1], selected_film, loc_residents[loc_num-1],
                                      srfc_waters[loc_num-1], terrains[loc_num-1], climates[loc_num-1])
