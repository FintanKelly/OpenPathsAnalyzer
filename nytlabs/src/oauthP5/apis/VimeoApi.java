package oauthP5.apis;

import oauthP5.oauth.Token;

public class VimeoApi extends DefaultApi10a
{
  private static final String AUTHORIZATION_URL = "http://vimeo.com/oauth/authorize?oauth_token=%s";
  
  @Override
  public String getAccessTokenEndpoint()
  {
    return "http://vimeo.com/oauth/access_token";
  }

  @Override
  public String getRequestTokenEndpoint()
  {
    return "http://vimeo.com/oauth/request_token";
  }
  
  @Override
  public String getAuthorizationUrl(Token requestToken)
  {
    return String.format(AUTHORIZATION_URL, requestToken.getToken());
  }
}
