package mpdproject.gcu.me.org.assignmenttest1;

/**
 * Created by robert on 24/03/18.
 * @author Robert McGuire S1429388

 */

public class ItemClass {

    private String title;
    private String description;
    private String link;
    private String pubDate;

    public ItemClass()
    {
        title = "";
        description = "";
        link = "";
        pubDate = "";
    }

    public ItemClass(String rssTitle, String rssDescription, String rssLink, String rssPubDate)

    {
        title = rssTitle;
        description = rssDescription;
        link = rssLink;
        pubDate = rssPubDate;

    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String rssTitle)
    {
        title = rssTitle;
    }

    public String getDescription()

    {
        return description;
    }

    public void setDescription(String rssDescription)
    {
        description = rssDescription;
    }

    public String getLink()
    {
        return link;
    }

    public void setLink(String rssLink)
    {
        link = rssLink;
    }

    public void getPubDate(String rssPubDate)
    {
        pubDate = rssPubDate;
    }

    public void setPubDate(String rssPubDate)
    {
        pubDate = rssPubDate;
    }


    public String toString()
    {
        String rssFeed;

        rssFeed = title + " " + description + " " + link + " " + pubDate;

        return rssFeed;
    }
}
