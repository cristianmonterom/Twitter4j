/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package twitter4j.examples.json;

import java.io.*;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import twitter4j.*;

/**
 *
 * @author cristian
 */
public class MyTimerTask extends TimerTask {

    @Override
    public void run() {
        System.out.println("Timer task started at:" + new Date());
        completeTask();
        System.out.println("Timer task finished at:" + new Date());
    }

    private void completeTask() {
        try {
            //assuming it takes 20 secs to complete the task
//          ConfigurationBuilder cb = new ConfigurationBuilder();
//cb.setDebugEnabled(true)
//.setOAuthConsumerKey("NUCv8sxLaqnhJYCG03lL30D2B")
//.setOAuthConsumerSecret("nuEr5D6iYoBBUCSHrJIhfrdZzYe3DvR36lvojRU6I0Kw8kdNos")
//.setOAuthAccessToken("128415623-5eYixO2hnMyBcJ6B3gQtHtJd5ZuDjwfcVVQz3Nxs")
//.setOAuthAccessTokenSecret("1jHgzOtCDH05tMym6kSPOPMQiUZQXQC4f33K83x1qNhSj")
//.setJSONStoreEnabled(true)
//.setUseSSL(true);
//
//TwitterFactory tf = new TwitterFactory(cb.build());
//Twitter twitter = tf.getInstance();   
        	
            Twitter twitter = new TwitterFactory().getInstance();
            System.out.println("Saving public timeline.");
            try {
                new File("statuses").mkdir();
                List<Status> statuses = twitter.getHomeTimeline();
                for (Status status : statuses) {
                    String rawJSON = TwitterObjectFactory.getRawJSON(status);
                    String fileName = "statuses/" + status.getId() + ".json";
                    storeJSON(rawJSON, fileName);
                    System.out.println(fileName + " - " + status.getText());
                }
                System.out.print("\ndone.");
//                System.exit(0);
            } catch (IOException ioe) {
                ioe.printStackTrace();
                System.out.println("Failed to store tweets: " + ioe.getMessage());
            } catch (TwitterException te) {
                te.printStackTrace();
                System.out.println("Failed to get timeline: " + te.getMessage());
                System.exit(-1);
            }
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String args[]) {
        TimerTask timerTask = new MyTimerTask();
        //running timer task as daemon thread
        Timer timer = new Timer(true);
        timer.scheduleAtFixedRate(timerTask, 0, 10 * 1000);
        System.out.println("TimerTask started");
        //cancel after sometime
        try {
            Thread.sleep(120000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        timer.cancel();
        System.out.println("TimerTask cancelled");
        try {
            Thread.sleep(30000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void storeJSON(String rawJSON, String fileName) throws IOException {
        FileOutputStream fos = null;
        OutputStreamWriter osw = null;
        BufferedWriter bw = null;
        try {
            fos = new FileOutputStream(fileName);
            osw = new OutputStreamWriter(fos, "UTF-8");
            bw = new BufferedWriter(osw);
            bw.write(rawJSON);
            bw.flush();
        } finally {
            if (bw != null) {
                try {
                    bw.close();
                } catch (IOException ignore) {
                }
            }
            if (osw != null) {
                try {
                    osw.close();
                } catch (IOException ignore) {
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException ignore) {
                }
            }
        }
    }
}
