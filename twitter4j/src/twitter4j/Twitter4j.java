//Teoricamente vale sincronizar

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package twitter4j;

import twitter4j.conf.ConfigurationBuilder;

/**
 *
 * @author cristian
 */
public class Twitter4j {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws TwitterException {
        // TODO code application logic here
//          Twitter twitter = TwitterFactory.getSingleton();
//    Query query = new Query("source:twitter4j yusukey");
//    QueryResult result = twitter.search(query);
//    for (Status status : result.getTweets()) {
//        System.out.println("@" + status.getUser().getScreenName() + ":" + status.getText());
//    }
    
    
    ConfigurationBuilder cb = new ConfigurationBuilder();
cb.setDebugEnabled(true)
  .setOAuthConsumerKey("NUCv8sxLaqnhJYCG03lL30D2B")
  .setOAuthConsumerSecret("nuEr5D6iYoBBUCSHrJIhfrdZzYe3DvR36lvojRU6I0Kw8kdNos")
  .setOAuthAccessToken("128415623-5eYixO2hnMyBcJ6B3gQtHtJd5ZuDjwfcVVQz3Nxs")
  .setOAuthAccessTokenSecret("1jHgzOtCDH05tMym6kSPOPMQiUZQXQC4f33K83x1qNhSj");
TwitterFactory tf = new TwitterFactory(cb.build());
Twitter twitter = tf.getInstance();

    }
}
