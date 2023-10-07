package cz.upce.fei.boop.pujcovna.kolekce;

import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * Tento interfejs deklaruje metody jednoduchého spojového seznamu. Spojový
 * seznam organizuje vkládané objekty, datové entity, do propojené řady.
 * <p>
 * Pro lepší pochopení pricipu spojového seznamu lze použít analogii s naloženým
 * nákladním vlakem. Každý vagón je spojen s následujícím vagónem a na každém
 * vagónu je naložen náklad. V našem případě jsou nákladem data typu dané
 * typovým parametrem E a vagóny jsou prvky seznamu.
 * <p>
 *
 * Popis hlavních sad metod podle jejich funkce:
 *
 * <p>
 * V tomto rozhraní jsou signatury metod pohybu po seznamu. A to proto, aby bylo
 * možné dosáhnout jakéhokoliv vložených dat v seznamu. Těmi metodami jsou
 * metody, které nastaví aktuální prvek seznamu na první pozici a potom další
 * metodou se posune pomyslný ukazatel na další prvek s datovými entitami v
 * seznamu. Další důležitou metodou je ta, která nastaví za aktuální data ty,
 * které jsou poslední, abychom mohli vkládat nová data na konec seznamu.
 * Proměnná s referencí na aktuální prvek seznamu bude implementována jako jeho
 * skrytý atribut.
 * <p>
 * Další metody zajišťují vložení, převzetí nebo vyjmutí dat do nebo ze seznamu
 * na pozici aktuálního prvku.
 * <p>
 *
 * <b>Poznámka</b>: Toto rozhraní obsahuje některé metody bežného lineárního
 * seznamu a některé speciální, kterými jsou ty, které používají implementaci s
 * aktuálním prvkem. S dalšími metoda se setkáte na předmětu datové struktury.
 *
 * <p>
 * Další sada metod umožňuje zjistit stav seznamu v podobě aktuálního počtu dat,
 * nebo zruší všechny vložené data.
 * <p>
 * Poslední a to zásadní je metoda, která dodá objekt iterátoru, který umožňuje
 * procházet data způsobem prvek po prvku, aniž by hrozilo nebezpečí, že bude
 * narušena souvislá řada vnitřních prvků seznamu od klientů.
 * <p>
 * Poznámky k implementaci:
 * <p>
 * Implementace spojového seznamu může být založena na principu speciálních
 * objektů, kterým budeme říkat prvek. Tyto prvky vytváří seznam podle
 * zpráv(metod), které zasílají klienti na instance s tímto rozhraním. Tyto
 * speciální objekty/prvky jsou vytvářeny podle staticky vnořené třídy nebo
 * podle vnitřní třídy. Která varianta je lepší je otázkou diskuze. Jedna
 * varianta je efektivnější z hlediska obsazení paměti. Obě dvě varianty mají v
 * našem případě dva atributy. Jeden atribut bude určen pro spojení s
 * následující prvkem seznamu a druhý atribut bude určen pro spojení s datovou
 * entitou
 * <p>
 * Jestliže bude vkládána datová entita na konec seznamu, tak do dosavadního
 * posledního prvku bude vložen odkaz na nově vzniklý prvek se spojením na
 * objekt datové entity typu E a odkaz na další prvek bude null, což indikuje,
 * že je konec seznamu.
 * <p>
 * Jiná, složitější, situace bude, když budeme vkládat nový prvek před nebo za
 * aktuální prvek. Složitější metody jsou ty, které vkládají před aktuální
 * prvek. Proto mají tyto metody již zde definovany defaultní těla (nová
 * vlastnost od Java 8). Tyto defaultní metody není nutné implementovat.
 * Implementaci těchto metod si mohou zvolit zdatnější studenti, při zachování
 * pricipu jednosměrného spojového seznamu.
 *
 * Vkládání dat do seznamu nemění polohu aktuálního prvku a nenastavuje.
 *
 * <b>Omezení implementace</b>
 * <ol>
 * <li>Spojový seznam musí být jednosměrný. To znamená, že pomocná vnitřní třída
 * Prvek obsahuje pouze jeden propojovací atribut pro uložení odkazu směrem na
 * další instanci třídy Prvek.
 * </ol>
 *
 * @author karel@simerda.cz
 *
 * @param <E>
 */
public interface Seznam<E> extends Iterable<E> {

    /**
     * Metoda pohybu nastavPrvni() nastaví vnitřní aktuální ukazatel na první
     * data seznamu.
     *
     * @throws KolekceException Výjimka se vystaví, když je seznam prázdný.
     */
    void nastavPrvni() throws KolekceException;

    /**
     * Metoda pohybu nastavPosledni() nastaví vnitřní aktuální ukazatel na
     * poslední data seznamu.
     *
     * @throws KolekceException Výjimka se vystaví, když je seznam prázdný.
     */
    void nastavPosledni() throws KolekceException;

    /**
     * Metoda pohybu dalsi()přestaví vnitřní aktuální ukazatel na další prvek
     * seznamu. V případě, že metoda zjistí, že nenásleduje žádný prvek v
     * seznamu vyvolá se výjimka.
     * <p>
     * Implementační upozornění: Tato metoda posouvá vnitřní ukazatel, který je
     * pro danou instanci součástí jejího stavu. Instanci seznamu lze taky
     * procházet iterátorem, který je zvláštním objektem, který bude vytvořen
     * podle vnitřní třídy s interfejsem Iterator. Každá žádost o iterátor
     * seznamu vytvoří nový objekt s vlastním ukazatelem na procházení položek
     * objektu seznamu.
     *
     *
     * @throws KolekceException Výjimka se vystaví v případě, že nebyl nastaven
     * aktuální prvek nebo byl v předchozím volání dosažen poslední prvek.
     */
    void dalsi() throws KolekceException;
    
    void predchozi() throws KolekceException;

    /**
     * Metoda zjišťuje, zda je následující prvek,
     *
     * @return Vrací hodnotu true, když aktuální prvke má následníka. V případě,
     * že ukazatel se přesunul na poslední prvek seznamu vrací se false.
     */
    boolean jeDalsi();

    /**
     * Vkládací metoda vlozPrvni() vloží data s datovou entitou jako první.
     *
     * Přechozí první data budou po zavolání metody druhými daty v seznamu.
     * Vložením se nemění poloha aktuálního prvku.
     *
     *
     * @param data Datová entita typu E
     *
     * @throws NullPointerException Výjimka se vystaví, když argument metody je
     * null.
     */
    void vlozPrvni(E data);

    /**
     * Vkládací metoda vlozPosledni() vloží nový prvek na konec seznamu.
     *
     * Jestliže je seznam prázdný, tak se data zařadí jako první. Vložením se
     * nemění poloha aktuálního prvku.
     *
     * @param data Datová entita typu E
     *
     * @throws NullPointerException Výjimka se vystaví, když argument metody je
     * null.
     */
    void vlozPosledni(E data);

    /**
     * Vkládací metoda vlozZaAktualni() vloží nový prvek s entitami type E za
     * aktuální prvek. Metoda musí rozpojit seznam a spojit ho zpět přes nově
     * vytvořený prvek.
     *
     * Analogie s vlakem: Rozdělení vlaku na dvě části. Druhá část po rozpojení
     * je odtažena na jinou kolej. Nový vagón s nákladem by se připojil k první
     * části a druhá část by se pak zpětně připojila za nově přidaný vagón.
     * <p>
     * V našem seznamu dojde k uložení odkazu na druhou část seznamu do nového
     * prvku a nový prvek se připopojí za aktuální. Přičemž ukazatel na aktuální
     * prvek se nemění.
     *
     *
     * @param data Datová entita typu E
     *
     * @throws KolekceException Výjimka se vystavá, když není nastaven aktuální
     * prvek.
     *
     * @throws NullPointerException Výjimka se vystaví, když argument metody je
     * null.
     */
    void vlozZaAktualni(E data) throws KolekceException;

    /**
     * Zjišťovací metoda jePrazdny() zjistí, zda seznam obsahuje prveky s daty.
     *
     * @return Vrací true, když je seznam prázdný, jinak false
     */
    boolean jePrazdny();

    /**
     * Přístupová metoda dejPrvni() vrátí odkaz na datovou entitu typu E prvního
     * prvku seznamu.
     *
     * @return Odkaz na datovou entitu typu E
     *
     * @throws KolekceException Výjimka se vystaví, když je seznam prázdný.
     */
    E dejPrvni() throws KolekceException;

    /**
     * Přístupová metoda dejPoslední() vrátí odkaz na datovou entitu typu E
     * posledního prvku seznamu.
     *
     * @return Odkaz na datovou entitu typu E
     *
     * @throws KolekceException Výjimka se vystaví, když je seznam prázdný.
     */
    E dejPosledni() throws KolekceException;

    /**
     * Přístupová metoda dejAktualni() vrátí v návratové hodnotě odkaz na
     * objekt, datovou entitu typu E, aktuálně nastaveného prvku metodami pohybu
     * po seznamu.
     *
     * @return vrací odkaz na object/datovou entitu typu E z aktuálního prvku
     * seznamu.
     *
     * @throws KolekceException Tato výjimka se vystaví, když je seznam prázdný
     * nebo když není nastaven aktualní prvek.
     */
    E dejAktualni() throws KolekceException;

    /**
     * Přístupová metoda dejZaAktualnim() vrátí v návratové hodnotě odkaz na
     * objekt, datovou entitu typu E, za aktuálně nastaveném prvku metodami
     * pohybu po seznamu.
     *
     * @return vrací odkaz na object/datovou entitu typu E za aktuálním prvkem
     * seznamu.
     *
     * @throws KolekceException Tato výjimka se vystaví, když je seznam prázdný
     * nebo když není nastaven aktuální prvek.
     */
    E dejZaAktualnim() throws KolekceException;

    /**
     * Metoda odeberPrvni() odebere první prvek ze seznamu.
     *
     * Metoda mění aktuální počet objektů v seznamu.
     *
     * Po odebrání prvního prvku se ukazatel na aktuální prvek nemění pokud
     * nebyl nastaven na prvni, tj. odebíraná, data. Když byl první prvek
     * zároveň aktuální, tak ukazatel na aktuální prvek bude po odebrání
     * nedefinován.
     *
     * @return Vrací odkaz na odebíraný objekt, datovou entitu typu E
     *
     * @throws KolekceException tato výjimka se vystaví, když byl seznam
     * prázdný.
     */
    E odeberPrvni() throws KolekceException;

    /**
     * Metoda odeberPosledni() odebere posledni prvek ze seznamu.
     *
     * Metoda mění aktuální počet objektů v seznamu.
     *
     * Po odebrání posledního prvku se ukazatel na aktuální prvek nemění pokud
     * nebyl nastaven na poslední, tj. odebíraný, prvek. Když byl poslední prvek
     * zároveň aktuální, tak ukazatel na aktuální prvek bude po odebrání
     * nedefinován.
     *
     * @return Vrací odkaz na odebíraný objekt, datovou entitu typu E
     *
     * @throws KolekceException tato výjimka se vystaví, když byl seznam
     * prázdný.
     */
    E odeberPosledni() throws KolekceException;

    /**
     * Metoda odeberAktualni() odebere aktuální prvek ze seznamu a zajistí
     * souvislé propojení zbylých prvků seznamu.
     *
     * Metoda mění aktuální počet objektů v seznamu.
     *
     * Po odebrání aktuálního prvku bude vnitřní ukazatel na aktuální
     * nedefinován.
     *
     * @return Vrací odkaz na odebíraný objekt, datovou entitu typu E
     *
     * @throws KolekceException tato výjimka se vystaví, když nebyl nastaven
     * aktuální prvek nebo byl seznam prázdný.
     */
    E odeberAktualni() throws KolekceException;

    /**
     * Metoda odeberZaAktualnim() odebere prvek ze seznamu za aktuálním prvkem a
     * zajistí souvislé propojení zbylých prvků seznamu.
     *
     * Metoda mění počet objektů v seznamu.
     *
     * @return Vrací odkaz na odebíraný objekt, datovou entitu typu E
     *
     * @throws KolekceException tato výjimka se vystaváí, když nebyl nastaven
     * aktuální prvek nebo byl seznam prázdný.
     */
    E odeberZaAktualnim() throws KolekceException;

    /**
     * Metoda size() vrací aktuální počet dat v seznamu
     *
     * @return Vrací hodnotu s počtem dat v seznamu.
     */
    int size();

    /**
     * Metoda zrus() odebere všechny data ze seznamu.
     */
    void zrus();

    /**
     * Metoda převede obsah seznamu na datový proud, který předá při návratu.
     *
     * Tato metoda se v implementačních třídách nepřekrývá.
     *
     * @return datovy proud
     */
    default Stream<E> stream() {
        return StreamSupport.stream(spliterator(), false);
    }

}
