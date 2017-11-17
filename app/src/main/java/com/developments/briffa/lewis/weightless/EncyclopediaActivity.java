package com.developments.briffa.lewis.weightless;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class EncyclopediaActivity extends AppCompatActivity {

    private GridView mGridView;
    private ArrayList<PlanetEntry> mPlanetEntries;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encyclopedia);

        mPlanetEntries = new ArrayList<>();
        mPlanetEntries.add(new PlanetEntry("Mercury", R.drawable.mercury_planet, "In Roman mythology Mercury is the god of commerce, travel and thievery, the Roman counterpart of the Greek god Hermes, the messenger of the Gods. The planet probably received this name because it moves so quickly across the sky.\n" +
                "\n" +
                "Mercury has been known since at least the time of the Sumerians (3rd millennium BC). It was sometimes given separate names for its apparitions as a morning star and as an evening star. Greek astronomers knew, however, that the two names referred to the same body. Heraclitus even believed that Mercury and Venus orbit the Sun, not the Earth.\n" +
                "\n" +
                "Since it is closer to the Sun than the Earth, the illumination of Mercury's disk varies when viewed with a telescope from our perspective. Galileo's telescope was too small to see Mercury's phases but he did see the phases of Venus.\n" +
                "\n" +
                "Mercury has been now been visited by two spacecraft, Mariner 10 and MESSENGER. Marriner 10 flew by three times in 1974 and 1975. Only 45% of the surface was mapped (and, unfortunately, it is too close to the Sun to be safely imaged by HST). MESSENGER was launched by NASA in 2004 and has been in orbit Mercury since 2011. Its first flyby in Jan 2008 provided new high quality images of some of the terrain not seen by Mariner 10. Since then Messenger has taken over 250,000 photographs coving the entire planet. Global Mosaics.\n" +
                "\n" +
                "The mission has provided support for the hypothesis that water ice and other volatiles do exist in the polar regions in permanent shadow.\n"));
        mPlanetEntries.add(new PlanetEntry("Venus", R.drawable.venus_planet, "Venus (Greek: Aphrodite; Babylonian: Ishtar) is the goddess of love and beauty. The planet is so named probably because it is the brightest of the planets known to the ancients. (With a few exceptions, the surface features on Venus are named for female figures.)\n" +
                "Venus has been known since prehistoric times. It is the brightest object in the sky except for the Sun and the Moon. Like Mercury, it was popularly thought to be two separate bodies: Eosphorus as the morning star and Hesperus as the evening star, but the Greek astronomers knew better. (Venus's apparition as the morning star is also sometimes called Lucifer.)\n" +
                "\n" +
                "Since Venus is an inferior planet, it shows phases when viewed with a telescope from the perspective of Earth. Galileo's observation of this phenomenon was important evidence in favor of Copernicus's heliocentric theory of the solar system.\n" +
                "\n" +
                "The first spacecraft to visit Venus was Mariner 2 in 1962. It was subsequently visited by many others (more than 20 in all so far), including Pioneer Venus and the Soviet Venera 7 the first spacecraft to land on another planet, and Venera 9 which returned the first photographs of the surface. The first orbiter, the US spacecraft Magellan produced detailed maps of Venus' surface using radar. ESA's Venus Express launched in November of 2005 and arrived at Venus in April 2006. The Venus Express is conducting atmospheric studies, mapping the Venusian surface temperatures and the plasma environment.\n" +
                "\n" +
                "Venus' rotation is somewhat unusual in that it is both very slow (243 Earth days per Venus day, slightly longer than Venus' year) and retrograde. In addition, the periods of Venus' rotation and of its orbit are synchronized such that it always presents the same face toward Earth when the two planets are at their closest approach. Whether this is a resonance effect or merely a coincidence is not known."));
        mPlanetEntries.add(new PlanetEntry("Earth", R.drawable.earth_planet, "Earth is the only planet whose English name does not derive from Greek/Roman mythology. The name derives from Old English and Germanic. There are, of course, hundreds of other names for the planet in other languages. In Roman Mythology, the goddess of the Earth was Tellus - the fertile soil (Greek: Gaia, terra mater - Mother Earth).\n" +
                "\n" +
                "It was not until the time of Copernicus (the sixteenth century) that it was understood that the Earth is just another planet.\n" +
                "\n" +
                "Earth, of course, can be studied without the aid of spacecraft. Nevertheless it was not until the twentieth century that we had maps of the entire planet. Pictures of the planet taken from space are of considerable importance; for example, they are an enormous help in weather prediction and especially in tracking and predicting hurricanes. And they are extraordinarily beautiful."));
        mPlanetEntries.add(new PlanetEntry("Mars", R.drawable.mars_planet, "Mars (Greek: Ares) is the god of War. The planet probably got this name due to its red color; Mars is sometimes referred to as the Red Planet. (An interesting side note: the Roman god Mars was a god of agriculture before becoming associated with the Greek Ares; those in favor of colonizing and terraforming Mars may prefer this symbolism.) The name of the month March derives from Mars.\n" +
                "\n" +
                "Mars has been known since prehistoric times. Of course, it has been extensively studied with ground-based observatories. But even very large telescopes find Mars a difficult target, it's just too small. It is still a favorite of science fiction writers as the most favorable place in the Solar System (other than Earth!) for human habitation. But the famous \"canals\" \"seen\" by Lowell and others were, unfortunately, just as imaginary as Barsoomian princesses. \n" +
                "\n" +
                "The first spacecraft to visit Mars was Mariner 4 in 1965. Several others followed including Mars 2, the first spacecraft to land on Mars and the two Viking landers in 1976. Ending a long 20 year hiatus, Mars Pathfinder landed successfully on Mars on 1997 July 4. In 2004 the Mars Expedition Rovers \"Spirit\" and \"Opportunity\" landed on Mars sending back geologic data and many pictures; they are still operating after more than three years on Mars. In 2008, Phoenix landed in the northern plains to search for water. Three Mars orbiters (Mars Reconnaissance Orbiter, Mars Odyssey, and Mars Express) are also currently in operation."));
        mPlanetEntries.add(new PlanetEntry("Jupiter", R.drawable.jupiter_planet, "Jupiter (a.k.a. Jove; Greek Zeus) was the King of the Gods, the ruler of Olympus and the patron of the Roman state. Zeus was the son of Cronus (Saturn).\n" +
                "Jupiter is the fourth brightest object in the sky (after the Sun, the Moon and Venus). It has been known since prehistoric times as a bright \"wandering star\". But in 1610 when Galileo first pointed a telescope at the sky he discovered Jupiter's four large moons Io, Europa, Ganymede and Callisto (now known as the Galilean moons) and recorded their motions back and forth around Jupiter. This was the first discovery of a center of motion not apparently centered on the Earth. It was a major point in favor of Copernicus's heliocentric theory of the motions of the planets (along with other new evidence from his telescope: the phases of Venus and the mountains on the Moon). Galileo's outspoken support of the Copernican theory got him in trouble with the Inquisition. Today anyone can repeat Galileo's observations (without fear of retribution :-) using binoculars or an inexpensive telescope.\n" +
                "\n" +
                "Jupiter was first visited by Pioneer 10 in 1973 and later by Pioneer 11, Voyager 1, Voyager 2 and Ulysses. The spacecraft Galileo orbited Jupiter for eight years. It is still regularly observed by the Hubble Space Telescope.\n" +
                "\n" +
                "The gas planets do not have solid surfaces, their gaseous material simply gets denser with depth (the radii and diameters quoted for the planets are for levels corresponding to a pressure of 1 atmosphere). What we see when looking at these planets is the tops of clouds high in their atmospheres (slightly above the 1 atmosphere level).\n" +
                "\n" +
                "Jupiter is about 90% hydrogen and 10% helium (by numbers of atoms, 75/25% by mass) with traces of methane, water, ammonia and \"rock\". This is very close to the composition of the primordial Solar Nebula from which the entire solar system was formed. Saturn has a similar composition, but Uranus and Neptune have much less hydrogen and helium."));
        mPlanetEntries.add(new PlanetEntry("Saturn", R.drawable.saturn_planet, "In Roman mythology, Saturn is the god of agriculture. The associated Greek god, Cronus, was the son of Uranus and Gaia and the father of Zeus (Jupiter). Saturn is the root of the English word \"Saturday\" (see Appendix 5).\n" +
                "\n" +
                "Saturn has been known since prehistoric times. Galileo was the first to observe it with a telescope in 1610; he noted its odd appearance but was confused by it. Early observations of Saturn were complicated by the fact that the Earth passes through the plane of Saturn's rings every few years as Saturn moves in its orbit. A low resolution image of Saturn therefore changes drastically. It was not until 1659 that Christiaan Huygens correctly inferred the geometry of the rings. Saturn's rings remained unique in the known solar system until 1977 when very faint rings were discovered around Uranus (and shortly thereafter around Jupiter and Neptune).\n" +
                "\n" +
                "Saturn was first visited by NASA's Pioneer 11 in 1979 and later by Voyager 1 and Voyager 2. Cassini (a joint NASA / ESA project) arrived on July 1, 2004 and will orbit Saturn for at least four years.\n" +
                "\n" +
                "Saturn is visibly flattened (oblate) when viewed through a small telescope; its equatorial and polar diameters vary by almost 10% (120,536 km vs. 108,728 km). This is the result of its rapid rotation and fluid state. The other gas planets are also oblate, but not so much so.\n" +
                "\n" +
                "Saturn is the least dense of the planets; its specific gravity (0.7) is less than that of water."));
        mPlanetEntries.add(new PlanetEntry("Uranus", R.drawable.uranus_planet, "Careful pronunciation may be necessary to avoid embarrassment; say \"YOOR a nus\" , not \"your anus\" or \"urine us\".\n" +
                "\n" +
                "Uranus is the ancient Greek deity of the Heavens, the earliest supreme god. Uranus was the son and mate of Gaia the father of Cronus (Saturn) and of the Cyclopes and Titans (predecessors of the Olympian gods).\n" +
                "\n" +
                "Uranus, the first planet discovered in modern times, was discovered by William Herschel while systematically searching the sky with his telescope on March 13, 1781. It had actually been seen many times before but ignored as simply another star (the earliest recorded sighting was in 1690 when John Flamsteed cataloged it as 34 Tauri). Herschel named it \"the Georgium Sidus\" (the Georgian Planet) in honor of his patron, the infamous (to Americans) King George III of England; others called it \"Herschel\". The name \"Uranus\" was first proposed by Bode in conformity with the other planetary names from classical mythology but didn't come into common use until 1850.\n" +
                "\n" +
                "Uranus has been visited by only one spacecraft, Voyager 2 on Jan 24 1986.\n" +
                "\n" +
                "Most of the planets spin on an axis nearly perpendicular to the plane of the ecliptic but Uranus' axis is almost parallel to the ecliptic. At the time of Voyager 2's passage, Uranus' south pole was pointed almost directly at the Sun. This results in the odd fact that Uranus' polar regions receive more energy input from the Sun than do its equatorial regions. Uranus is nevertheless hotter at its equator than at its poles. The mechanism underlying this is unknown.\n" +
                "\n" +
                "Actually, there's an ongoing battle over which of Uranus' poles is its north pole! Either its axial inclination is a bit over 90 degrees and its rotation is direct, or it's a bit less than 90 degrees and the rotation is retrograde. The problem is that you need to draw a dividing line *somewhere*, because in a case like Venus there is little dispute that the rotation is indeed retrograde (not a direct rotation with an inclination of nearly 180)."));
        mPlanetEntries.add(new PlanetEntry("Neptune", R.drawable.neptune_planet, "In Roman mythology Neptune (Greek: Poseidon) was the god of the Sea.\n" +
                "\n" +
                "After the discovery of Uranus, it was noticed that its orbit was not as it should be in accordance with Newton's laws. It was therefore predicted that another more distant planet must be perturbing Uranus' orbit. Neptune was first observed by Galle and d'Arrest on 1846 Sept 23 very near to the locations independently predicted by Adams and Le Verrier from calculations based on the observed positions of Jupiter, Saturn and Uranus. An international dispute arose between the English and French (though not, apparently between Adams and Le Verrier personally) over priority and the right to name the new planet; they are now jointly credited with Neptune's discovery. Subsequent observations have shown that the orbits calculated by Adams and Le Verrier diverge from Neptune's actual orbit fairly quickly. Had the search for the planet taken place a few years earlier or later it would not have been found anywhere near the predicted location.\n" +
                "\n" +
                "More than two centuries earlier, in 1613, Galileo observed Neptune when it happened to be very near Jupiter, but he thought it was just a star. On two successive nights he actually noticed that it moved slightly with respect to another nearby star. But on the subsequent nights it was out of his field of view. Had he seen it on the previous few nights Neptune's motion would have been obvious to him. But, alas, cloudy skies prevented observations on those few critical days.\n" +
                "\n" +
                "Neptune has been visited by only one spacecraft, Voyager 2 on Aug 25 1989. Much of we know about Neptune comes from this single encounter. But fortunately, recent ground-based and HST observations have added a great deal, too.\n" +
                "\n" +
                "Because Pluto's orbit is so eccentric, it sometimes crosses the orbit of Neptune making Neptune the most distant planet from the Sun for a few years."));

        mGridView = (GridView) findViewById(R.id.gridview_encyclopedia);
        mGridView.setAdapter(new ImageAdapter(this));
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(EncyclopediaActivity.this, "" + position, Toast.LENGTH_LONG);
            }
        });
    }

    public class ImageAdapter extends BaseAdapter {

        public ImageAdapter(Context context) {

        }

        @Override
        public int getCount() {
            return 8;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            View itemView = getLayoutInflater().inflate(R.layout.item_encyclopedia, null);
            ImageView imageView = itemView.findViewById(R.id.imageView);
            TextView textView = itemView.findViewById(R.id.textView);
            imageView.setImageResource(mPlanetEntries.get(position).getImage());
            textView.setText(mPlanetEntries.get(position).getName());
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent encyclopediaDetailsIntent = new Intent(EncyclopediaActivity.this, EncyclopediaDetailsActivity.class);
                    encyclopediaDetailsIntent.putExtra("NAME", mPlanetEntries.get(position).getName());
                    encyclopediaDetailsIntent.putExtra("ID", mPlanetEntries.get(position).getImage());
                    encyclopediaDetailsIntent.putExtra("DESCRIPTION", mPlanetEntries.get(position).getDescription());
                    startActivity(encyclopediaDetailsIntent);
                }
            });
            return itemView;
        }
    }
}
