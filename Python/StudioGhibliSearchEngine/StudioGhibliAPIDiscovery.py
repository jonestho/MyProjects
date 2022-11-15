import GhibliFcns


while True:
    command = input('What would you like to do?\n'
                    '(1) Search Movies by Year Range and rate them\n'
                    '(2) Find out about some characters\n'
                    '(3) Learn about a location and its population(IN PROGRESS)\n'
                    '(4) TBD\n'
                    '(5) What\'s this all about?\n'
                    '(6) Quit\n\n')
    if command == '1':
        GhibliFcns.movieSearch('https://ghibliapi.herokuapp.com/films')
    elif command == '2':
        GhibliFcns.peopleSearch('https://ghibliapi.herokuapp.com/people')
    elif command == '3':
        GhibliFcns.locationSearch('https://ghibliapi.herokuapp.com/locations')
    elif command == '4':
        print('\nComing soon!\n')
        input('Press any key to continue: \n')
    elif command == '5':
        print("\nThis interactive program, created by me, consists of basic algorithms that will retrieve, extract, and"
              " show data from the Studio Ghibli API by request. \nLike the program I wrote for my Intro to CompSci cou"
              "rse, this one is extended and has more features. I've always been a fan of Studio Ghibli and thought\n"
              "it'd be fun to try and create a fun little program that retrieves information and does other tweaks."
              "\n\nThank you for reading this and I hope you enjoy! (Studio Ghibli API can be accessed at https://ghibl"
              "iapi.herokuapp.com)\n")

        input('Press any key to continue: \n')
    elif command == '6':
        break
    else:
        print('Invalid prompt. Try again\n')


input('\nThank you for your time! Press any key to exit: ')
