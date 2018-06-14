package starter

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.{EnableAutoConfiguration, SpringBootApplication}
import org.springframework.context.annotation.{ComponentScan, Configuration}


@Configuration
@EnableAutoConfiguration
@ComponentScan
@SpringBootApplication
class Config


object Starter extends App {

    SpringApplication.run(classOf[Config])

}
