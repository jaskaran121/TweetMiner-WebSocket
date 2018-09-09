package service;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.stream.IntStream;

import twitter4j.GeoLocation;
import twitter4j.HashtagEntity;
import twitter4j.MediaEntity;
import twitter4j.Place;
import twitter4j.Query;
import twitter4j.RateLimitStatus;
import twitter4j.ResponseList;
import twitter4j.Scopes;
import twitter4j.Status;
import twitter4j.SymbolEntity;
import twitter4j.TwitterException;
import twitter4j.URLEntity;
import twitter4j.User;
import twitter4j.UserMentionEntity;
/**
 * This MockTwitterApi program handles
 * testing of the program.
 * 
 * @author Jaskaran Singh
 * @author Gagandeep Singh
 */


public class MockTwitterApi implements TwitterApi {
	
    public static class mockStatus implements Status {

    	String username;
    	String tweet;
    	String locatioName;
    	Double longitude;
    	Double lattitude;
    	List<String> hashtags;
    	
		/**
		 * @param username
		 * @param tweet
		 * @param locatioName
		 * @param longitude
		 * @param lattitude
		 * @param hashtags
		 */
		public mockStatus(String username, String tweet, String locatioName,List<String> hashtags,Double longitude,Double lattitude) {
			this.username = username;
			this.tweet = tweet;
			this.locatioName = locatioName;
			this.hashtags = hashtags;
			this.lattitude=lattitude;
			this.longitude=longitude;
		}

		@Override
		public int compareTo(Status arg0) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public RateLimitStatus getRateLimitStatus() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public int getAccessLevel() {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public UserMentionEntity[] getUserMentionEntities() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public URLEntity[] getURLEntities() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public HashtagEntity[] getHashtagEntities() {
			// TODO Auto-generated method stub
			
			HashtagEntity[] l= new HashtagEntity[this.hashtags.size()];
			IntStream.range(0, this.hashtags.size()).forEach(i->{l[i]=new mockHashTagEntiy(this.hashtags.get(i));});
			
			return l;
		}

		@Override
		public MediaEntity[] getMediaEntities() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public SymbolEntity[] getSymbolEntities() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Date getCreatedAt() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getId() {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public String getText() {
			// TODO Auto-generated method stub
			return tweet;
		}

		@Override
		public int getDisplayTextRangeStart() {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public int getDisplayTextRangeEnd() {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public String getSource() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public boolean isTruncated() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public long getInReplyToStatusId() {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public long getInReplyToUserId() {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public String getInReplyToScreenName() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public GeoLocation getGeoLocation() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Place getPlace() {
			// TODO Auto-generated method stub
			Place p=new mockPlace(this.locatioName, this.longitude, this.lattitude);
			return p;
		}

		@Override
		public boolean isFavorited() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean isRetweeted() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public int getFavoriteCount() {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public User getUser() {
			// TODO Auto-generated method stub
			return new mockUser(this.username);
		}

		@Override
		public boolean isRetweet() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public Status getRetweetedStatus() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long[] getContributors() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public int getRetweetCount() {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public boolean isRetweetedByMe() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public long getCurrentUserRetweetId() {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public boolean isPossiblySensitive() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public String getLang() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Scopes getScopes() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public String[] getWithheldInCountries() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getQuotedStatusId() {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public Status getQuotedStatus() {
			// TODO Auto-generated method stub
			return null;
		}

    }
     

    public static class mockHashTagEntiy implements HashtagEntity{
    	String hashtag;
    	
		/**
		 * @param hashtag
		 */
		public mockHashTagEntiy(String hashtag) {
			this.hashtag = hashtag;
		}

		@Override
		public String getText() {
			// TODO Auto-generated method stub
			return hashtag;
		}

		@Override
		public int getStart() {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public int getEnd() {
			// TODO Auto-generated method stub
			return 0;
		}
    	
    }
    
    public static class mockPlace implements Place{
    	String fullName;
    	Double longitude;
    	Double lattitude;
		/**
		 * @param fullName
		 */
		public mockPlace(String fullName,Double longitude,Double lattitude) {
			this.fullName = fullName;
			this.lattitude=lattitude;
			this.longitude=longitude;
		}

		@Override
		public RateLimitStatus getRateLimitStatus() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public int getAccessLevel() {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public int compareTo(Place arg0) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public String getName() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public String getStreetAddress() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public String getCountryCode() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public String getId() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public String getCountry() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public String getPlaceType() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public String getURL() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public String getFullName() {
			// TODO Auto-generated method stub
			return fullName;
		}

		@Override
		public String getBoundingBoxType() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public GeoLocation[][] getBoundingBoxCoordinates() {
			// TODO Auto-generated method stub
			GeoLocation[][] gl= new GeoLocation[1][1];
			gl[0][0]=new GeoLocation(this.lattitude, this.longitude);
			return gl;
		}

		@Override
		public String getGeometryType() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public GeoLocation[][] getGeometryCoordinates() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Place[] getContainedWithIn() {
			// TODO Auto-generated method stub
			return null;
		}
    	
    }
    
    public static class mockUser implements User{
    	
    	String screenName;
		/**
		 * @param screenName
		 */
		public mockUser(String screenName) {
			this.screenName = screenName;
		}

		@Override
		public int compareTo(User o) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public RateLimitStatus getRateLimitStatus() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public int getAccessLevel() {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public long getId() {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public String getName() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public String getEmail() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public String getScreenName() {
			// TODO Auto-generated method stub
			return this.screenName;
		}

		@Override
		public String getLocation() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public String getDescription() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public boolean isContributorsEnabled() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public String getProfileImageURL() {
			// TODO Auto-generated method stub
			return "Dummy Url";
		}

		@Override
		public String getBiggerProfileImageURL() {
			// TODO Auto-generated method stub
			return "Dummy Big Url";
		}

		@Override
		public String getMiniProfileImageURL() {
			// TODO Auto-generated method stub
			return "Dummy mini profile Url";
		}

		@Override
		public String getOriginalProfileImageURL() {
			// TODO Auto-generated method stub
			return "Dummy profile image url";
		}

		@Override
		public String getProfileImageURLHttps() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public String getBiggerProfileImageURLHttps() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public String getMiniProfileImageURLHttps() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public String getOriginalProfileImageURLHttps() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public boolean isDefaultProfileImage() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public String getURL() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public boolean isProtected() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public int getFollowersCount() {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public Status getStatus() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public String getProfileBackgroundColor() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public String getProfileTextColor() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public String getProfileLinkColor() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public String getProfileSidebarFillColor() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public String getProfileSidebarBorderColor() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public boolean isProfileUseBackgroundImage() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean isDefaultProfile() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean isShowAllInlineMedia() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public int getFriendsCount() {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public Date getCreatedAt() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public int getFavouritesCount() {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public int getUtcOffset() {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public String getTimeZone() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public String getProfileBackgroundImageURL() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public String getProfileBackgroundImageUrlHttps() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public String getProfileBannerURL() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public String getProfileBannerRetinaURL() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public String getProfileBannerIPadURL() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public String getProfileBannerIPadRetinaURL() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public String getProfileBannerMobileURL() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public String getProfileBannerMobileRetinaURL() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public boolean isProfileBackgroundTiled() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public String getLang() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public int getStatusesCount() {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public boolean isGeoEnabled() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean isVerified() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean isTranslator() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public int getListedCount() {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public boolean isFollowRequestSent() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public URLEntity[] getDescriptionURLEntities() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public URLEntity getURLEntity() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public String[] getWithheldInCountries() {
			// TODO Auto-generated method stub
			return null;
		}
    	
    }
    
    /**
	 * Generates Mock Tweets for testing.
	 * 
	 * @param  searchString instance of type Query         
	 * @return list of 10 mock tweets
	 * @author Jaskaran
	 */
	@Override
	public List<Status> getQueryResult(Query q) {
		// TODO Auto-generated method stub
		List<Status> l= new ArrayList<>();
		for(int i=0; i<10; i++) {
			Status s= new mockStatus("username "+i, "Mock Tweet for testing"+i , "MockLocation"+i, Arrays.asList("testHashtag1","testHashtag2","testHashtag3"), 45.516136, 73.656830);
			l.add(s);
		}
		return l;
	}


	/**
	 * Generates Mock user for testing.
	 * 
	 * @param  screenName of type String.          
	 * @return instance of mockUser.
	 * @author Gagan
	 */
	@Override
	public User showUser(String screenName) throws TwitterException {
		// TODO Auto-generated method stub
		return new mockUser("UserFOrTesting");
	}


	/**
	 * Generates Mock UserTimeline data for testing.
	 * 
	 * @param  screenName of type String.         
	 * @return list of 10 mock tweets.
	 * @author Gurdip
	 */
	@Override
	public List<Status> getUserTimeline(String screenName) throws TwitterException {
		// TODO Auto-generated method stub
		List<Status> l= new ArrayList<>();
		 	
		for(int i=0; i<10; i++) {
			Status s= new mockStatus("username "+i, "Mock Tweet for testing"+i , "MockLocation"+i, Arrays.asList("testHashtag1","testHashtag2","testHashtag3"), 45.516136, 73.656830);
			l.add(s);
		}
	
		return  l;
	}

}
