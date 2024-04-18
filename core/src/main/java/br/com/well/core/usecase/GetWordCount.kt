package br.com.well.core.usecase

import br.com.well.core.data.Note

class GetWordCount {
    operator fun invoke(note: Note): Int {
        var wordCount = 0
        wordCount += getCount(note.title)
        wordCount += getCount(note.content)
        return wordCount
    }

    private fun getCount(string: String): Int {
        return string.split(" ", "\n").count {
            it.contains(Regex(".*[a-zA-Z].*"))
        }
    }
}