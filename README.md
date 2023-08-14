This is my final project in Android java course.



the project's target:
- Aid to Holocaust survivors.
- Recruiting volunteers (without commitment on their part) for associations working for Holocaust survivors.
- Immediate help for Holocaust survivors when needed.
- To encourage volunteering among people who are more busy in their day.




Server components from firebase:

  Authentication - login by phone/Gmail of the volunteer and the Holocaust survivor
RealTimeDataBase - the list of requests from Holocaust survivors





Project's description:

The application has 5 screens: an opening screen, a login screen for a volunteer, a login screen for a Holocaust survivor, a screen for a volunteer and a screen for a Holocaust survivor.

On the opening screen there are three buttons, so the top one is big and prominent for connecting Holocaust survivors and the association's employees, and below it are 2 smaller buttons for registering Holocaust survivors and volunteers.
Any holocaust survivor will be able to use the application, but for that, he (or any relative) must contact an association (of any kind) that will transfer the information to the programmer so that he can add the survivor's details to the DataManager, and thus the survivor's connection will only be made if there is a match between his ID card and those written in the list.

The main goal of the app is not to create commitment among the volunteers and actually to allow them to volunteer spontaneously, thus encouraging more people to volunteer. Therefore, their login screen will only require a phone/email and this is only so that the association can be in control (communication with the volunteer and the survivor/contact person) that the survivor did receive what he requested. This will be done so that every request that a volunteer clicks on (undertakes to carry out), the request will enter the screen of the association's employees with the communication details with which the volunteer chose to connect.

The Holocaust survivors screen will include 5 buttons so that each of them has a specific request. The request is written large and clearly and the survivor/association (subject to the decision of the association) only has to click on the request. The request will be updated in the top fragment of the volunteer screen which contains the entire list of requests. of all the survivors
When a volunteer clicks on one of the components in the list, the component will "move" from the general list to the lower fragment of the volunteer (only that specific volunteer can see this screen) and thus will see the volunteer/volunteers he has undertaken to perform now.
