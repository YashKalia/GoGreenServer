### Product
#### What design decisions have you made? (technological choices, architectural choices)

1. What are some of the major decisions that you have taken as a team?
2. Which technological choices did you make and why? (e.g., Swing versus JavaFX, yes/no abstract base class, …)
    * Use Mockito for tests because an embedded database for tests proved too difficult to implement in the timeframe we had
    * Use JavaFX over Swing because Swing event handling isn't supported and also with the use of SceneBuilder, I could create a much better looking GUI than Swing.
    * For the cliet side we deicded to use RestTemplate objects  for communicating to the server(instead of HTTPURLConnection objects) 
     and for testing as well as it was a concept that covered a lot of what we needed in terms of code. 
    

### Process
#### How did the project go? (process-wise)

Add bullet points to any of the following:

1. Did you manage to stick to the planning (why (not))
2. How did the collaboration in team go?
    * I would say I communicated fine overall with the other team members but the week before Demo 3, there was a miscommunication from my part which caused my contributions to Demo 3 very limited.
    * The collaboartion  among the team members was satisfactory.
3. How did you communicate?
    * Though I preferred to use mobile messenging for the most part I found it handy to attach comments directly to the commits.
4. How did version control help (if at all)
    * Git was very helpful in allowing us to work in separate branches simultaneously so that different parts of the app were being developed at the same time.
5. What did you learn?
    *Working in a team is the most important lesson to take away from the project and it really served as a look into how work is divided and distributed in big projects as well.

### Reflection
#### What can be improved? (reflection on process, product and course)

Add bullet points to any of the following:

1. How can your software be improved (testing, GUI, code
quality, features, …)
    * Integration Tests: make sure everything works from one end to the other
2. How can the process/collaboration be improved?
3.  *
3. How can the course be improved?
    * I would suggest to give a more detailed topic to work on the project instead of a very vague topic that has many different ways of execution.
    * I would have preferred to have a small guide on brightspace that can help you work around new and hard to understand topics like Mockito for testing as 
    * finding relevant information regarding that was very tedious and it was a topic that should have been broached during the lectures.

### Individual feedback

1. Each team member reflects on how he/she functioned in the
team (at least 200 words per team member)
    * What were your stronger/weaker points during this project
    * Did you have conflicts with other team members? How did you
solve them?

* ***Do it yourself***

### Value Sensitive Design
#### This section is for the Ethics aspects in the OOP Project. You can find the guidelines in the slides of the second Ethics lecture.

Consider the product (program) you are designing/developing for this course. Choose:

* at least 1 not obvious stakeholder, in addition to a generic user and/or the ”client” (see examples in slides)
    * OR
* at least 1 not obvious value for which you may want to design (see examples in slides) in addition to environmental-friendliness or other more traditional values i.e. safety, innovation, usability, effectiveness, efficiency.


Add bullet points to any of the following:

1. How would you define your main concept: “design for…”
2. How would you define in general terms the stakeholders/values you want to design for
    * *Transparency*: Design for transparency, so that every user is aware of which data we are collecting and how we are using it.
3. Which sources would you consult in order to gain theoretical insights into the stakeholders or values you want to design for: academic literature, artworks, experts in domain different than yours…; give some general indication not necessarily titles of books or names of experts: e.g. “social scientists who have study the phenomenon X, documentaries on discrimination of the group Y, legal/philosophical literature on the right to Z, political studies on the meaning of democracy etc.)
    * *Transparency*: Employ lawyers to get us up to code with GDPR regulations, and also psychologists who can give input on how to approach this subject with users
4. Your concept involves the realization of at least two values OR one or more values for at least two different stakeholders (see above). 
   
Explain:
* How the interests of the different chosen stakeholders AND/OR the different values may be in tension
* How you may try to loosen this tension by some new design solution (also hypothetical, non-existing)
