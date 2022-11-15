class Movie:
    def __init__(self, title='None', description='None', release_date=0, director='None', rt_score=0):
        self.title = title
        self.description = description
        self.release_date = release_date
        self.director = director
        self.rt_score = rt_score

    def __str__(self):
        return '\nMovie Information:\n' \
               '\nTitle: {}\n' \
               'Year released: {}\n' \
               'Director: {}\n' \
               'Description: {}\n' \
               'Rotten Tomatoes Score: {}\n'.format(self.title, self.release_date, self.director, self.description,
                                                    self.rt_score)

    # The update_score method averages out the two scores: the Rotten Tomatoes score and the score given by the user.

    def update_score(self, new_score):
        if 0 < new_score <= 100:
            self.rt_score = str((int(self.rt_score) + new_score) // 2)


class Character:
    def __init__(self, name='None', film='None', gender='None', age=0, hair_color='None', species='Unknown'):
        self.name = name
        self.film = film
        self.gender = gender
        self.age = age
        self.hair_color = hair_color
        self.species = species

    def __str__(self):
        return '{} is a {} year old {} with {} hair.' \
               ' This character\'s species is {} and is from {}.'.format(self.name, self.age, self.gender,
                                                                         self.hair_color, self.species, self.film)


class Location:
    def __init__(self, name='None', film='None', residents='None', srfc_water=0, terrain='None', climate='None'):
        self.name = name
        self.film = film
        self.residents = residents
        self.srfc_water = srfc_water
        self.terrain = terrain
        self.climate = climate
