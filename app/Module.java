import com.google.inject.AbstractModule;

import service.ActualTwitterApi;
import service.TwitterApi;

/**
 * This class binds TwitterApi class to
 * ActualTwitterApi class.
 */

public class Module extends AbstractModule{

	@Override
	protected void configure() {
		 bind(TwitterApi.class)
         .to(ActualTwitterApi.class);
      
		 }

}
