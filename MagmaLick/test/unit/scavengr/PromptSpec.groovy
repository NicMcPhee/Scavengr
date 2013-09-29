package scavengr

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Prompt)
class PromptSpec extends Specification {
    def blank = ''
    @Override
    def setup() {
    }

    @Override
    def cleanup() {
    }

    def "testPromptConstraints"() {
        when:
        def prompt1 = new Prompt(title: 'testPrompt1', description: 'testPromptDescription1', myHunt: new Hunt())
        def prompt2 = new Prompt(title: 'testPrompt2', description: blank, myHunt: new Hunt())
        def prompt3 = new Prompt(title: blank, description: 'testPromptDescription3', myHunt: new Hunt())
        def prompt4 = new Prompt(title: blank, description: blank, myHunt: new Hunt())
        mockForConstraintsTests(Prompt, [prompt1, prompt2, prompt3, prompt4])

        then:
        prompt1.validate()
        prompt2.validate()
        !prompt3.validate()
        !prompt4.validate()
    }

    def "testToString"() {
        when:
        def p5 = 'testPrompt5'
        def p6 = 'testPrompt6'
        def prompt5 = new Prompt(title: p5, description: 'testPromptDescription5', myHunt: new Hunt())
        def prompt6 = new Prompt(title: p6, description: blank, myHunt: new Hunt())

        then:
        prompt5.toString() == p5
        prompt6.toString() == p6
    }
}