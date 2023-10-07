package cz.upce.fei.boop.pujcovna.kolekce;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author karel@simerda.cz
 */
public class SpojovySeznamTest {

    /**
     * Testovací třída pro ověření implementace třídy SpojovySeznam
     */
    private static class TestClass {

        int a;

        public TestClass(int a) {
            this.a = a;
        }

        @Override
        public String toString() {
            return "T" + a;
        }

    }
    /***
     * Sada instancí testovací třídy pro ověření implementace třídy SpojovySeznam
     */
    private final TestClass T1 = new TestClass(1);
    private final TestClass T2 = new TestClass(2);
    private final TestClass T3 = new TestClass(3);
    private final TestClass T4 = new TestClass(4);
    private final TestClass T5 = new TestClass(5);
    private final TestClass T6 = new TestClass(6);
    private final TestClass T7 = new TestClass(7);
    private final TestClass T8 = new TestClass(8);
    private final TestClass T9 = new TestClass(9);
  

    public SpojovySeznamTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

// TODO Každou veřejnou metodu třídy SpojovySeznam ověřte alespoň jedním testem  
// TODO Dosáhněte 100% pokrytí třídy SpojovySeznam tímto testem    
    
// Ukázka jednoduchého testu    
    @Test
    public void test_01_01_VlozPrvni() {
        try {
            LinkSeznam<TestClass> instance = new LinkSeznam<>();
            instance.vlozPrvni(T1);
            TestClass[] expected = {T1};
            TestClass[] result = {instance.dejPrvni()};          
            assertEquals(expected, result);
        } catch (Exception ex) {
            fail();
        }
    }
// Ukázka testu s vícenásobnou kontrolou stavu testované instance
    @Test
    public void test_01_02_VlozPrvni() {
        try {
            LinkSeznam<TestClass> instance = new LinkSeznam<>();
            instance.vlozPrvni(T1);
            instance.vlozPrvni(T2);
            TestClass[] result = {instance.dejPrvni(), instance.dejPosledni()};
            TestClass[] expected = {T2, T1};
            assertArrayEquals(expected, result);
        } catch (Exception ex) {
            fail();
        }
    }
//
//  Ukázka testu s vyvoláním výjimky   
    @Test(expected = NullPointerException.class)
    public void test_01_04_VlozPrvni() throws KolekceException {
        LinkSeznam<TestClass> instance = new LinkSeznam<>();
        instance.vlozPrvni(null);
        fail();
    }
    @Test
    public void test_02_01_Size(){
        try{
        LinkSeznam<TestClass> instance = new LinkSeznam<>();
        instance.vlozPrvni(T1);
        instance.vlozPrvni(T2);
        int test = 2;
        assertEquals(test,instance.size());
        }
        catch(Exception ex){
            fail();
        }
    }
    
    @Test
    public void test_03_01_NastavPrvni(){
        try{
        LinkSeznam<TestClass> instance = new LinkSeznam<>();
        instance.vlozPrvni(T1);
        instance.vlozPrvni(T2);
        instance.vlozPrvni(T3);
        TestClass must = T3;
        instance.nastavPrvni();
        TestClass res = instance.dejAktualni();
        assertEquals(res, must);
        }catch(Exception ex){
            fail();
        }
    }
    
    @Test(expected = KolekceException.class)
    public void test_03_02_NastavPrvni() throws KolekceException {
        LinkSeznam<TestClass> instance = new LinkSeznam<>();
        instance.nastavPrvni();
        fail();
    }
    
    @Test
    public void test_04_01_NastavPosledni(){
        try{
            LinkSeznam<TestClass> instance = new LinkSeznam<>();
            instance.vlozPrvni(T1);
            instance.vlozPrvni(T2);
            instance.vlozPrvni(T3);
            instance.nastavPosledni();
            TestClass must = T1;
            TestClass res = instance.dejAktualni();
            assertEquals(res, must);
        }catch(Exception ex){
            fail();
        }
    }
    
    @Test(expected = KolekceException.class)
    public void test_04_02_NastavPosledni() throws KolekceException {
        LinkSeznam<TestClass> instance = new LinkSeznam<>();
        instance.nastavPosledni();
        fail();
    }
    
    @Test
    public void test_05_01_Dalsi() throws KolekceException{
        LinkSeznam<TestClass> instance = new LinkSeznam<>();
        instance.vlozPrvni(T1);
        instance.vlozPrvni(T2);
        instance.nastavPrvni();
        instance.dalsi();
        TestClass must = T1;
        TestClass res = instance.dejAktualni();
        assertEquals(res, must);
    }
    
    @Test(expected = KolekceException.class)
    public void test_05_02_Dalsi() throws KolekceException {
        LinkSeznam<TestClass> instance = new LinkSeznam<>();
        instance.vlozPrvni(T1);
        instance.vlozPrvni(T2);
        instance.dalsi();
        fail();
    }
    
    @Test
    public void test_06_01_vlozPosledni() throws KolekceException {
        LinkSeznam<TestClass> instance = new LinkSeznam<>();
        instance.vlozPosledni(T1);
        instance.vlozPosledni(T2);
        TestClass must = T2;
        TestClass result = instance.dejPosledni();
        assertEquals(must, result);
    }
    
    @Test(expected = KolekceException.class)
    public void test_06_02_vlozPosledni() throws KolekceException {
        LinkSeznam<TestClass> instance = new LinkSeznam<>();
        TestClass result = instance.dejPosledni();
        fail();
    }
    
    @Test
    public void test_07_01_testdejZaAktualnim() throws KolekceException{
        LinkSeznam<TestClass> instance = new LinkSeznam<>();
        instance.vlozPosledni(T1);
        instance.vlozPosledni(T2);
        instance.vlozPosledni(T3);
        TestClass must = T3;
        instance.nastavPrvni();
        instance.dalsi();
        TestClass result = instance.dejZaAktualnim();
        assertEquals(must, result);
    }
    
    @Test
    public void test_08_01_testvlozZaAktualni() throws KolekceException{
        LinkSeznam<TestClass> instance = new LinkSeznam<>();
        instance.vlozPrvni(T1);
        instance.nastavPrvni();
        instance.vlozZaAktualni(T2);
        TestClass must = T2;
        TestClass result = instance.dejZaAktualnim();
        assertEquals(must, result);
    }
    
    @Test
    public void test_08_02_testvlozZaAktualni() throws KolekceException{
        LinkSeznam<TestClass> instance = new LinkSeznam<>();
        instance.vlozPrvni(T1);
        instance.vlozPosledni(T2);
        instance.nastavPrvni();
        instance.vlozZaAktualni(T3);
        TestClass must = T3;
        TestClass result = instance.dejZaAktualnim();
        assertEquals(must, result);
    }
    
    @Test
    public void test_09_01_testodeberPrvni() throws KolekceException{
        LinkSeznam<TestClass> instance = new LinkSeznam<>();
        instance.vlozPrvni(T1);
        instance.vlozPrvni(T2);
        instance.vlozPrvni(T3);
        TestClass must = T3;
        TestClass result = instance.odeberPrvni();
        assertEquals(must, result);
    }
    
    @Test
    public void test_10_01_odeberPosledni() throws KolekceException{
        LinkSeznam<TestClass> instance = new LinkSeznam<>();
        instance.vlozPrvni(T1);
        instance.vlozPrvni(T2);
        instance.vlozPrvni(T3);
        TestClass must = T2;
        instance.odeberPosledni();
        TestClass result = instance.dejPosledni();
        assertEquals(must, result);
    }
    
    @Test
    public void test_11_01_odeberAktualni() throws KolekceException{
        LinkSeznam<TestClass> instance = new LinkSeznam<>();
        instance.vlozPrvni(T1);
        instance.vlozPrvni(T2);
        instance.vlozPrvni(T3);
        instance.vlozPrvni(T4);
        instance.nastavPrvni();
        instance.dalsi();
        instance.dalsi();
        TestClass must = T2;
        TestClass result = instance.odeberAktualni();
        assertEquals(must, result);
    }
    
    @Test
    public void test_12_01_odeberZaAktualni() throws KolekceException{
        LinkSeznam<TestClass> instance = new LinkSeznam<>();
        instance.vlozPrvni(T1);
        instance.vlozPrvni(T2);
        instance.vlozPrvni(T3);
        instance.vlozPrvni(T4);
        instance.nastavPrvni();
        instance.dalsi();
        TestClass must = T2;
        TestClass result = instance.odeberZaAktualnim();
        assertEquals(must, result);
    }
    
    @Test(expected = KolekceException.class)
    public void test_13_01_zrus() throws KolekceException {
        LinkSeznam<TestClass> instance = new LinkSeznam<>();
        instance.vlozPrvni(T1);
        instance.vlozPrvni(T2);
        instance.vlozPrvni(T3);
        instance.vlozPrvni(T4);
        instance.zrus();
        instance.dejPrvni();
        fail();
    }
    
       @Test
public void test_17_01_Iterator() {
    try {
        LinkSeznam<TestClass> instance = new LinkSeznam<>();
        instance.vlozPrvni(T2);
        instance.vlozPrvni(T1);
        instance.nastavPrvni();
        boolean result = instance.iterator().hasNext();
        boolean expected = true;
        assertEquals(expected, result);
    } catch (KolekceException ex) {
        fail();
    }
}
@Test
public void test_17_02_Iterator() {
    try {
        LinkSeznam<TestClass> instance = new LinkSeznam<>();
        instance.vlozPrvni(T3);
        instance.vlozPrvni(T2);
        instance.vlozPrvni(T1);
        instance.nastavPrvni();
        TestClass result = instance.iterator().next();
        TestClass expected = T1;
        assertEquals(expected, result);
    } catch (KolekceException ex) {
        fail();
    }
}
}
