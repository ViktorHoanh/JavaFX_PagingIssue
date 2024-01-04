package controller;

import entity.media.Media;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class controls the flow of events in homescreen
 *
 */
public class HomeController extends BaseController {

    /**
     * this method gets all Media in DB and return back to home to display
     *
     * @return List[Media]
     * @throws SQLException
     */
    public List getAllMedia() throws SQLException {
        return new Media().getAllMedia();
    }

    /**
     * this method gets all Media in DB where title like "name" and return back to home to display
     *
     * @return List[Media]
     * @throws SQLException
     */
    public List getMediaByName(String name) throws SQLException {
        return new Media().getMediaByName(name);
    }

}
