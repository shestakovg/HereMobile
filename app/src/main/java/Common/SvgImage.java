package Common;
import android.app.Activity;
import android.view.View;
import android.widget.ImageView;

import com.larvalabs.svgandroid.SVG;
import com.larvalabs.svgandroid.SVGParser;
/**
 * Created by gshestakov on 11/14/2017.
 */

public class SvgImage {
    private static ImageView imageView;
    private Activity activity;
    private SVG svg;
    private int xmlLayoutId;
    private int drawableId;


    public SvgImage(Activity activity, int layoutId, int drawableId) {
        imageView = (ImageView) activity.findViewById(layoutId);
        svg = SVGParser.getSVGFromResource(activity.getResources(), drawableId);
        //Needed because of image accelaration in some devices such as samsung
        imageView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        imageView.setImageDrawable(svg.createPictureDrawable());
    }
}
