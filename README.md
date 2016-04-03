# CapOneSES
Capital One Software Engineering Summit Challenge - Srishti Sanya Submission

**note: in the development environment, the following dependencies need to be satisfied:
Constructed on the Android API 17 Platform \n
JDK 1.8 \n
Jsoup 1.8.3

Functionality
------------------

Put a URL of an article in the text field. Hit 'Summarize,' and wait a bit for it to load.
If you'd like the keyboard to minimize while you read, press 'Done.'

Rationale:
-------------------------
We were asked to create a program that takes in an article and returns to the user a summary of the article. 
What I did was create a program that takes into account multiple definitions of the word 'summary.'
There is the more consumerist definition, where a summary refers to a shortened version of the content that is more digestible to a user only looking to receive a basic shortening of a longer article
And there is the more literal definition, in which all of the aspects of the article are broken down into consumable pieces of information.
We took this to mean word count, number of paragraphs, links within the article body, and author information. 

"if I wanna make it return something it'll have to be an array"
	Why: inefficient to create multiple inputs in main for each function when they all almost always run simultaneously. They all have one function: to help summarize an article. 

What was a challenge
-----------------------------
Articles used to be written in what was called "inverse pyramid format." It was an industry standard, where the most important facts were written at the beginning of the article, and the rest of the article decreased in importance from there. 
In recent journalistic practice, a number of writers have elected to shift to a more conversational, narrative-style tone, especially when writing nontraditional articles such as human interest peices. 
However, most publications and informative articles have still elected to keep the same inverse pyramid format.
Multiple publicans don't use the same standards for HTML tagging in their web design, though we were able to correctly assume that one publication would generally have one standard. 

What could be better
-------------
Machine learning, instead of picking from the article you create your own sentece constructions
Network graphs, so that when picking up important terms and searching them out in the article, it can search for synonyms or realted terms as well, so that the results aren't so disparate
Also, the list of words to ignore will grow over time, as the system gradually learns more about what keywords to ignore


we didn't use intent, we used asynchronous threads because that's necessary to go out to the web and retrieve information
We'd porbably use intent

The Process:
-------
THe most experience I'd had with mobile development was design and prototyping. 
