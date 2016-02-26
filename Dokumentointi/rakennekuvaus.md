### Karkeimmalla tasolla

Luokka Peli sisältää metodit, joiden avulla luokan Sankari ja Morko toteuttavat oliot kykenevät pieksemään toisiaan osana peliä. Pelin ja Kayttiksen yhteistoiminta kulminoituu siihen kuinka Kayttis voi kertoa Pelille pelaajan toivoman toiminnon ja Peli Kayttikselle mitä inforuutuun tulisi kirjoittaa.

##### Sankarin puolella

Sankariin liittyvät sen toteuttavat aliluokat (tässä tapauksessa vain Soturi) ja Interface Ase toteuttavat oliot. Aseet toimittavat Sankarin hyökkäysmetodille tarvittavan satunnaisgeneroidun vahingon.

##### Mörköjen mailla

Morko luokka on siitä Sankari-luokkaa yksinkertaisempi että siihen liittyvät vain sen aliluokat. Näiden erikoistaidot ja hyökkäysten vahingontuotto ovat erilaiset, mutta toimintaperiaate on yleensä sama. Poikkeuksena Luurankokuningas jolla on ainoana Morkona Peli-luokan ohittava mahdolisuus vahingoittaa Sankarin ilmentymiä.
