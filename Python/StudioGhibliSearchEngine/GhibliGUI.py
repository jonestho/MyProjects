# from kivy.app import App
# from kivy.uix.gridlayout import GridLayout
# from kivy.uix.label import Label
# from kivy.uix.image import Image
# from kivy.uix.button import Button
# from kivy.uix.textinput import TextInput
#
#
# class GhibliGUI(App):
#     def __init__(self):
#         super().__init__()
#         self.greeting = Label(text="What would you like to do?")
#         self.window = GridLayout()
#         self.button1 = Button(text="Search Movies by Year Range and rate them")
#
#     def build(self):
#         self.window.cols = 1
#         # add widgets to window
#
#         # image widget
#         self.window.add_widget(Image(source="my_neighbor_totoro_vector_by_firedragonmatty_d6wn40g-pre.png"))
#         # label widget
#         self.window.add_widget(self.greeting)
#         # button widget 1
#         self.window.add_widget(self.button1)
#
#         return self.window
