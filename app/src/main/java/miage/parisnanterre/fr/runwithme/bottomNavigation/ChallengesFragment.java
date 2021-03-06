package miage.parisnanterre.fr.runwithme.bottomNavigation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.nightonke.boommenu.BoomButtons.ButtonPlaceEnum;
import com.nightonke.boommenu.BoomButtons.TextInsideCircleButton;
import com.nightonke.boommenu.BoomMenuButton;
import com.nightonke.boommenu.ButtonEnum;
import com.nightonke.boommenu.Piece.PiecePlaceEnum;

import java.util.ArrayList;

import miage.parisnanterre.fr.runwithme.Challenges.VraiFaux;
import miage.parisnanterre.fr.runwithme.R;
import miage.parisnanterre.fr.runwithme.sandwich.ObjectifNutritionActivity;
import miage.parisnanterre.fr.runwithme.sandwich.SandwichActivity;


public class ChallengesFragment extends Fragment {
    // The onCreateView method is called when Fragment should create its View object hierarchy,
    // either dynamically or via XML layout inflation.
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        // Defines the xml file for the fragment
        return inflater.inflate(R.layout.fragment_challenges, parent, false);
    }
    @Override
    public void onDetach() {
        super.onDetach();
    }
    @Override
    public void onStop() {
        super.onStop();
    }

    // This event is triggered soon after onCreateView().
    // Any view setup should occur here.  E.g., view lookups and attaching view listeners.
    private BoomMenuButton bmb;
    private ArrayList<Pair> piecesAndButtons = new ArrayList<>();
    LinearLayout soloLayout;
    LinearLayout grpLayout;
    LinearLayout eventLayout;
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // Setup any handles to view objects here
        // EditText etFoo = (EditText) view.findViewById(R.id.etFoo);

        soloLayout = (LinearLayout) view.findViewById(R.id.solo);
        grpLayout = (LinearLayout) view.findViewById(R.id.grp);
        eventLayout = (LinearLayout) view.findViewById(R.id.event);
        final BoomMenuButton bmb = (BoomMenuButton) view.findViewById(R.id.bmb);
        bmb.setButtonEnum(ButtonEnum.TextInsideCircle);
        bmb.setPiecePlaceEnum(PiecePlaceEnum.DOT_9_1);
        bmb.setButtonPlaceEnum(ButtonPlaceEnum.SC_9_1);
        for (int i = 0; i < bmb.getPiecePlaceEnum().pieceNumber(); i++) {
            TextInsideCircleButton.Builder builder = new TextInsideCircleButton.Builder()
                    .normalImageRes(R.drawable.butterfly)
                    .normalText("Butter Doesn't fly!");
            bmb.addBuilder(builder);
        }
        /*
        bmb.setPiecePlaceEnum(PiecePlaceEnum.DOT_3_1);
        bmb.setButtonPlaceEnum(ButtonPlaceEnum.SC_3_3);
        for (int i = 0; i < bmb.getButtonPlaceEnum().buttonNumber(); i++) {
            bmb.addBuilder(new SimpleCircleButton.Builder()
                    .normalImageRes(R.drawable.ic_event));
        }*/

        grpLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bmb.performClick();
                bmb.boom();
            }
        });
        soloLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ObjectifNutritionActivity.class);//SandwichActivity
                startActivity(intent);
            }
        });
        eventLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), VraiFaux.class);
                startActivity(intent);
            }
        });


    }
}