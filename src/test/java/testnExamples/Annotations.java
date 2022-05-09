package testnExamples;

import org.testng.annotations.*;

public class Annotations {




    @BeforeSuite (alwaysRun = true)  // for group tests
    public void beforeSuite(){
        System.out.println("beforeSuite");
    }

    @BeforeTest
    public void beforeTEst(){
        System.out.println("beforeTEst");

    }


    @BeforeGroups("smoke")
    public void beforegroups(){
        System.out.println("beforeTEst");

    }
    @AfterGroups("smoke")
    public void aftergroups(){
        System.out.println("beforeTEst");

    }


    @BeforeClass
    public void beforeClass(){
        System.out.println("beforeClass");
    }


    @BeforeMethod
    public void beforeMethod(){

        System.out.println("beforeMethod");

    }


    @Test
    public void test1(){

        System.out.println("test1");

    }

    @Test (groups = {"smoke"})
    public void test2(){

        System.out.println("test2");

    }

    @AfterMethod
    public void afterMethod(){

        System.out.println("afterMethod");
    }


    @AfterClass
    public void afterClass(){

        System.out.println("afterClass");

    }

    @AfterTest
    public void afterTest(){

        System.out.println("afterTest");

    }

    @AfterSuite
    public void afterSuite(){
        System.out.println("afterSuite");

    }




}
