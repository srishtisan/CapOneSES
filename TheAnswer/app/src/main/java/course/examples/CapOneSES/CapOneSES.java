package course.examples.CapOneSES;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CapOneSES extends Activity {

	// URL Address
	String url = "http://www.nyu.edu";
	// not actually used, just for field-filling purposes
	ProgressDialog mProgressDialog;
	public static final String detText = " ";
	TextView summaryView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		//For constructing the screen
		String mdetText = " ";
		super.onCreate(savedInstanceState);
		setContentView(R.layout.answer_layout);

		if (savedInstanceState != null) {
			mdetText = savedInstanceState.getString(detText," ");
		}

		final EditText urlText = (EditText) findViewById(R.id.urlText);
		Button button = (Button) findViewById(R.id.button);
		summaryView = (TextView) findViewById(R.id.summaryView);

		summaryView.setText(mdetText);

		button.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				// Execute Title AsyncTask
				url = urlText.getText().toString();
				new Title().execute();
			}
		});
	}

	// Title AsyncTask needed, as synchronous call to url is not allowed in Android to avoid freezing of device
	private class Title extends AsyncTask<Void, Void, Void> {
		String sumNdet;
        // manually selected common words to discount in keyword process
		String[] cmnWords = new String[] {"a","an","the","is","are","was","were", "has","had","been", "will","would", "can","could","shall","should",
				"I","you","he","she","me","guy","girl","gal","don't","It's","it","they","mine","your","our","his","her","him","their","these","those","do","did",
				"and","mention","New","really","several","high","low","faster","have","had","will","at","The","be","being","been","many","some","few",
				"by","for","during","During","just","over","with","there","then","in","it","even","if","other","now","could","would","should","so","such",
				"while","than","and","been","an","be","to","this","that","of","or","but","But","its","he","He","between","you","You","I","he","they","from",
				"said","on","to","one","year","years","month","months","day","days","January","February","March","April","May","June","July","August","September","October","November","December","before","after",
				"see","all","last","first","second","third","very","few","number","before","after","what","when","where","how","always","never","such",
				"in","into","more","—","about","”","also","remaining","similar","happen","somebody","To","not","mine","your","go","going","gone","went",
				"get","put","because","still","such","great","who","whose","whom","up","down","so","as",
				"them","us","out","in"};
		String bodycontent[];
		String tmpStr = " ";
		String paraStr = " ";
		Map<String, Integer> map = new HashMap<String, Integer>();

		@Override
		protected void onPreExecute() {
            //First part of AsyncThread
			super.onPreExecute();
			mProgressDialog = new ProgressDialog(CapOneSES.this);
			mProgressDialog.setTitle("Srishti Sanya CapOne SES");
			mProgressDialog.setMessage("Loading...");
			mProgressDialog.setIndeterminate(false);
			mProgressDialog.show();
		}

		@Override
		protected Void doInBackground(Void... params) {
			try {
				// Connect to the web site
				Document document = Jsoup.connect(url).get();
				// Get the html document title
				sumNdet = "Title : \n" + document.title() + "\n\n";
                // "sumNdet" meaning "Summary and details"

                //Split into paragraphs
				Elements paras = document.select("article[itemprop=\"articleBody\"]");
				bodycontent = paras.text().split("[ .,]+");
				//
// 				Isolate the body into paragraphs so that we can find the most important paragraph
				paraStr = " ";
				int i = 0;
				for (i=0; i<paras.select("p").size();i++) {
                    //split into words
					paraStr = paraStr + paras.select("p").get(i).text() + "\n";
				}
                // Construct the full summary + details view by pieces
                // Extract "summary:
				sumNdet = sumNdet + "Summary:" + "\n" + paras.select("p").get(0).text() + "\n\n";
                // Extract Author
				Elements author = document.select("span[itemprop=\"name\"]");
				sumNdet = sumNdet + "Author: \n" + author.text() + "\n\n";
                //Extract Author Bio
				Elements authorBio = document.getElementsByClass("post-body-bio");
				sumNdet = sumNdet + "Author Bio: \n" + authorBio.text() + "\n\n";
                //Extract links in body of text
				Elements links = document.body().select("a[href^=http:]");
				tmpStr =" ";
				for (i=0; i<links.size();i++) {
					if (i >10){break;}
					tmpStr = tmpStr + links.get(i).attr("href").toString() + "\n";
				}
                // Show links in body
				sumNdet = sumNdet + "Links on the page: \n" + tmpStr + "\n\n";
                // count total number of paragraphs
				sumNdet = sumNdet + "Total Number of Paragraphs: " + paras.select("p").size() + "\n\n";



// 				Create a word and add to displayed frequency list (...)
//              (...)only if the word is not a common word listed above

				for (String keyword: bodycontent){
					Integer n = map.get(keyword);
					n = (n == null) ? 1: ++n;
					if (!Arrays.asList(cmnWords).contains(keyword)){map.put(keyword.toLowerCase(),n);}
				}
//				Print the frequently used words with criteria frequency>3, common words have been removed
				sumNdet = sumNdet + "Most important keywords : \n";
				tmpStr = " ";
				for (Object variableName: map.keySet()){

					if (map.get(variableName) > 3){
						tmpStr = tmpStr + variableName +" : " + map.get(variableName) + "\n\n";
					}
				}
				sumNdet = sumNdet + tmpStr;

//				End of keyword identification
                // Print original article
				sumNdet = sumNdet + "Original Article:" + "\n" + paraStr;
				paraStr = " ";
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			// Set title into TextView
//			TextView summaryView = (TextView) findViewById(R.id.summaryView);
            // For scrolling purposes
			summaryView.setMovementMethod(new ScrollingMovementMethod());
			summaryView.setText(sumNdet);
			mProgressDialog.dismiss();
		}
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
        //For portrait to landscape recreation
		// Save the values you need from your textview into "outState"-object
		super.onSaveInstanceState(outState);
		outState.putString(detText, summaryView.getText().toString());
	}
}
