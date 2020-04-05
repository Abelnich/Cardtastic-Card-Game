package Cards;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.ConsoleHandler;

import javafx.scene.image.Image;

// this class stores images of all the cards

public class CardImage {
    private static final String ImageLocal = "";
    private static final String ImageSuff = ".jpg";
    private static final String[] RankSymbols = {"a", "2", "3", "4", "5", "6", "7", "8", "9", "t", "j", "q", "k"};
    private static final String[] SuitLetters = {"c", "d", "h", "s"};

    private static Map<String, Image> gCards = new HashMap<String, Image>();

    private CardImage()
    {}



    // getCard() returns the image of a card. Parameter cardImg is the specific card
    public static Image getCard( Card cardImg )
    {
        assert cardImg != null;
        return getCard( getCode( cardImg ) );
    }


    // getBackOfCard() returns an image of the back of the card
    public static Image getBackOfCard()
    {
        return getCard( "b" );
    }

    private static String getCode( Card cardImg )
    {
        return RankSymbols[ cardImg.getRank().ordinal() ] + SuitLetters[ cardImg.getSuit().ordinal() ];
    }

    private static Image getCard( String pCode )
    {
        Image img = (Image) gCards.get( pCode );
        if( img == null )
        {
            System.out.println(ImageLocal + pCode + ImageSuff);
            img = new Image(CardImage.class.getClassLoader().getResourceAsStream( ImageLocal + pCode + ImageSuff ));
            gCards.put( pCode, img );
        }
        return img;
    }
}
