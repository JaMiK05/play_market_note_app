package uz.gita.play_market_note_app.data.model

class Id private constructor() {

    var id: Long = 0

    companion object {
        private lateinit var comp: Id

        fun getInstance(): Id {
            if (!::comp.isInitialized) {
                comp = Id()
            }
            return comp
        }

    }

}