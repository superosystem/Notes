package org.orbitfiftyeight.unscramble.game

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.orbitfiftyeight.unscramble.R
import org.orbitfiftyeight.unscramble.databinding.FragmentGameBinding

class GameFragment : Fragment() {

    private var score = 0
    private var currentWordCount = 0
    private var currentScrambledWord = "test"

    private lateinit var binding : FragmentGameBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGameBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Setup a click listener for submit and skip buttons
        binding.submit.setOnClickListener { onSubmitWord() }
        binding.skip.setOnClickListener { onSkipWord() }

        updateNextWordOnScreen()

        binding.score.text = getString(R.string.scores, 0)
        binding.wordCount.text = getString(R.string.word_count, 0, MAX_NO_OF_WORDS)
    }

    private fun onSubmitWord() {
        currentScrambledWord = getNextScrambledWord()
        currentWordCount++
        score += SCORE_INCREASE
        binding.wordCount.text = getString(R.string.word_count, currentWordCount, MAX_NO_OF_WORDS)
        binding.score.text = getString(R.string.scores, score)
        setErrorTextField(false)
        updateNextWordOnScreen()
    }

    private fun onSkipWord() {
        currentScrambledWord = getNextScrambledWord()
        currentWordCount++
        binding.wordCount.text = getString(R.string.word_count, currentWordCount, MAX_NO_OF_WORDS)
        setErrorTextField(false)
        updateNextWordOnScreen()
    }

    private fun getNextScrambledWord(): String {
        val tempWord = allWordsList.random().toCharArray()
        tempWord.shuffle()
        return String(tempWord)
    }

    private fun setErrorTextField(error: Boolean) {
        if (error) {
            binding.textField.isErrorEnabled = true
            binding.textField.error = getString(R.string.try_again)
        } else {
            binding.textField.isErrorEnabled = false
            binding.textInputEditText.text = null
        }
    }

    private fun updateNextWordOnScreen() {
        binding.textViewUnscrambledWord.text = currentScrambledWord
    }

    private fun restartGame() {
        setErrorTextField(false)
        updateNextWordOnScreen()
    }

    private fun exitGame() {
        activity?.finish()
    }
}