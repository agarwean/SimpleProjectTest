package com.grzegorz.it.configuration;

import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;

public abstract class AbstractArquillianTest {

    protected static WebArchive prepareBaseArchive() {
        return ShrinkWrap.create(WebArchive.class)
                .addClasses(AbstractArquillianTest.class)
                .addAsResource("META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }

}
