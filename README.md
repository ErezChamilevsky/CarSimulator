## Car Simulator 
This program is a little car simulator codeded using Java. This program came as an idea to sharp tools such as design patterns, OOP and coding in general.

## Project Architecture
This project I implemented, for the first time, the MVC architectural design pattern. I have treid to separate between the View, Display, and the Model, WorldGenerator.
In Control.java, the Controller, it is gotten orders and makes updates of both model and view.

## Folder Structure
In the src folder there are few folders:
CarControl - Car and CarControlPanel are the model's objects of the car and it's safty. Control is the Controllers of the program.
GUI - where are all the GUI components.
Photos - imgs that are used in the program.
Safty - passing lanes and blind spot senesors. It supposed to be more developed but evnetually there was no need.
World - the model's folder, and the model itself - WorldGenerator.

## Design patterns
In addition to MVC I used other design patterns such as:
Singleton - Display (the view), WorldGenerator (the model), Car, CarControlPanel.
Abstract Factory - in safty folder. At first, it suppossed to have a lane alert but there was no need.
Delegation - using Rectangle as a field of each model's object that represents in the GUI and utilize it's functions and methods.

## Interfaces
In addition to Abstract use in Safety there are few interfaces that apllied the Polymorphism Principle of OOP:
World - for all the model's objects that have represent in the GUI.
RepresentGUI - for all the view's objects that draws in the GUI

The use of polymorphism was needed in order to manage both model and view's objects, espacially by the controller (with HashMap).

## Notes
The GUI package has created by my University. Here the doc https://u.cs.biu.ac.il/~kleinay/biuoop-1.4-doc/ .


![ezgif-1-f8a2c98764](https://github.com/ErezChamilevsky/CarSimulator/assets/118533992/2b121c5a-8f2c-42b6-b14b-90041a9bf266)

The yellow circle is a blind spot sensor's alert.


https://github.com/ErezChamilevsky/CarSimulator/assets/118533992/dac98fb8-4b81-47ef-b2bc-9da58f29366d


