package pages

import geb.Page 
class CreateAHuntPage extends Page{
        def input = 'input'
        def button = 'button'
        static uri = 'hunt/create'

        static at = {
            title.endsWith('Create Hunt')
        }

        static content = {

            createHuntButton() {$(button , id:'createHuntButton')}

            huntTitleBox() {$(input, id:'title')}
            huntDescriptionBox() {$('textarea', id:'description')}
            huntStartDate() {$(input, id:'startDate')}
            huntEndDate() {$(input, id:'endDate')}

            promptTitleBox(){$(input, id:'promptTitle' ) }
            promptDescriptionBox() {$('textarea', id:'promptDescription') }
            addPromptButton(){ $(button, id:'addPromptButton') }
            removePromptButton() {$(button, id:'remove0') }
            
            emailBox() {$(input, id:'new-email')}
            emailButton() {$(button, id:'add-email-button')}
            deleteEmailButton() {$(button, id:'remove0')}
        }
    }
