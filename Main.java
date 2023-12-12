package khnu.mizhfac;


import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Slf4j(topic =  "hgedfhgfd.fgsdhsdf.fhsdahesdf.sdfagasd.sdgaewaqr")
public class Main {

    public static void main(String[] args) {
        log.trace("log trace");
        log.debug("log debug");
        log.info("{}", formMessage());
        log.warn("log warn");
        log.error("log error");
        log.atInfo()
                        .log(Main::formMessage);
        System.out.println("The happy end");
    }
    static  String formMessage(){
        log.warn("formMessage executed");
        return "The message";
    }
}