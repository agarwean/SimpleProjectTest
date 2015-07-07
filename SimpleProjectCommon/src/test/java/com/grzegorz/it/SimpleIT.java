package com.grzegorz.it;

import com.grzegorz.it.configuration.AbstractArquillianTest;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(Arquillian.class)
public class SimpleIT extends AbstractArquillianTest {

    @Deployment
    public static WebArchive createJarArchive() {
        return prepareBaseArchive().addPackages(true, "org.assertj");
    }

    @Test
    public void pass() {
        assertThat(Boolean.TRUE).isTrue();
    }

}
