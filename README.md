# CapOneSES
Capital One Software Engineering Summit Challenge - Srishti Sanya Submission

*note: in the development environment, the following dependencies need to be satisfied:*
* Constructed on the Android API 17 Platform
* JDK 1.8
* Jsoup 1.8.3

Screenshot: 
----
![alt text](https://github.com/srishtisan/CapOneSES/blob/master/IMG_8441.PNG)

Functionality:
------------------
To run on an Android device:
Download the APK onto the device, and then run. 

Paste or type a URL of an article into the text field. (The program is optimized for Washington Post articles, explanation following).

Hit 'Summarize,' and wait a bit for the results to load. On most modern devices, this should be no longer than a couple of seconds. 


Rationale:
-------------------------
We were asked to create a program that takes in an article and returns to the user a summary of that article. 
What I did was create a program that takes into account multiple definitions of the word 'summary.'
There is the more general-use definition, where a summary refers to a shortened version of the content that is more digestible to a user only looking to receive a basic understanding of a longer article.
There is also the more literal definition, in which all of the aspects of the article are broken down into consumable pieces of information. When incorporating this, I took this to mean word count, number of paragraphs, links within the article body, and author information. 


What was a challenge:
-----------------------------
Articles used to be written in what was called "inverse pyramid format." It was an industry standard, where the most important facts were written at the beginning of the article, and the rest of the article provided more details from there. 
In recent journalistic practice, a number of writers have elected to shift to a more conversational, narrative-style tone, especially when writing nontraditional articles such as human interest peices. 
However, most publications and informative articles have still elected to keep the same inverse pyramid format.
**
Multiple publications don't use the same standards for HTML tagging in their web design, though my research has indicated that one publication generally has one standard. This is why the program is optimized for use with articles from the Washington Post.


What could be better:
-------------
The project could be expanded to include aspects of machine learning, where, instead of picking from the article, the program creates its own sentence constructions for an original summary. This extends into NLP at this point. 
The construction could use network graphs, so that when picking up important terms and searching them out in the article, it can search for synonyms or related terms as well, so that the results aren't so disparate.
Also, the list of words to ignore would grow over time, as the system gradually learns more about what keywords to ignore.

I didn't use intent, and hence intent filters, in lieu of asynchronous threads because the parsing required a great deal of simultaneous processing. The asynchronous processes can be run on multiple threads. I believe this can also be achieved with intent, and could probably use intent on a subsequent rebuild. 

This could be expanded using a web crawler, summarizing related articles with simple calls by the user. Furthermore, this could pull together all articles posted in a small enough time frame, summarizing each of them to create a sort of summarized newspaper, though this would require significant processing and large amounts  of research that I do not have at the moment.

Error handling should be improved. Currently, when the user does not input anything into the text box, or if the link inputted does not follow the HTML structure assumed, the system does not have graceful handling of the situation. 

The UI could stand some sprucing up. A more user-focused UI would likely include more clearly delineated sections, possibly through the usage of varying section header sizes. It could also be improved by adding collapsible and expandable sections so that the user can focus on one type of information at a time. 

The Process:
-------
The most experience I'd had with mobile development before this was in design and prototyping, which is why I had originally elected to create a Chrome extension. This would have worked excellently, had I not written the program entirely in Java, reliant on Jsoup functionality. This, I learned close to the deadline, was a problem. With the serendipitous weeklong extension, I weighed my options between rewriting the entire program in Javascript, or adapting the execution to something Java-friendly. It was then that I chose to make this Android-compatible, and had to quickly learn how that process works. I do believe my programming skills have expanded significantly and quickly as a result, and although I would still like to explore implementation through the extension, the kick to learn Android programming has been greatly beneficial. 
