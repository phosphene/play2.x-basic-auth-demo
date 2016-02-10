package acceptance

import org.scalatest._

import play.test._
import org.openqa.selenium.phantomjs.PhantomJSDriver
import play.api.test.FakeApplication
import play.api.test.TestServer
import play.api.test.Helpers._

import play.api.Play
import model.Account


/**
  * Acceptance testing using PhantomJS and FeatureSpec syntax
  * Note that Given, When, and Then are capitalized as then is
  * a reserved word in latter day Scala
  * note the use of before and after blocks which said convention is mixed in using
  * BeforeAndAfter trait
  */

class UserSignInFeatureSpec extends FeatureSpec with GivenWhenThen with BeforeAndAfter with Matchers {

  var browser: TestBrowser = _
  var server: TestServer = _


  before {
    val appWithMemoryDatabase = FakeApplication(additionalConfiguration = inMemoryDatabase())
    server = new TestServer(3333, appWithMemoryDatabase)
    server.start()
    browser = Helpers.testBrowser(new PhantomJSDriver())
  }

  after {
    server.stop()
    browser.quit()
  }

  feature("Signing in") {

    info("As a user")
    info("I want to Sign in")

    scenario("I'm a user going to the website") {

      Given("I am a user")
      When("I browse to the website")
      browser.goTo("http://localhost:3333/")

      Then("I should see the Sign In page")
      browser.pageSource should include("Sign in")
    }

    scenario("I want to sign in as a credentialed user") {

      Given("I have correct credentials")

      When("I attempt to sign in")
      browser.goTo("http://localhost:3333/")
      browser.$("#email").text("alice@example.com")
      browser.$("#password").text("secret")
      browser.$("#loginbutton").click()

      Then("I should have a live session")
      browser.$("dl.error").size should be(0)
      browser.pageSource should not include ("Sign in")
      browser.pageSource should include ("logout")
      browser.getCookie("PLAY2AUTH_SESS_ID").getExpiry should not be (null)
    }

    scenario("I want to sign in as a non-credentialed user") {

      Given("I do not have correct credentials")

      When("I attempt to sign in")
      browser.goTo("http://localhost:3333/")
      browser.$("#email").text("alice@example.com")
      browser.$("#password").text("secretxxx")
      browser.$("#loginbutton").click()

      Then("I should be denied")
      browser.pageSource should include("Invalid email or password")
    }
  }
}
