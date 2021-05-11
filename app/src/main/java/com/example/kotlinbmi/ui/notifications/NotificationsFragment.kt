package com.example.kotlinbmi.ui.notifications

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.kotlinbmi.R

class NotificationsFragment : Fragment() {

    private lateinit var notificationsViewModel: NotificationsViewModel


    fun Context.toast(message: CharSequence) =
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

    var questionNo = 0
    var questions = listOf(
        "How did COVID 19 infect the first human? \n\n A) some Chinese ate a bat \n\n B) got out of a virus laboratory located in the city where the infection was detected \n\n C) aliens implanted it with a special probe",
        "Where Covid19 was separated? \n\n A) in China \n\n B) in Pakistan \n\n C) in the USA",
        "Which country is in the top three for coronavirus spread and deaths? \n\n A) Russia \n\n B) Italy \n\n C) Poland",
        "According to the latest Irish research, what fraction of Covid 19 infections has occurred outside? \n\n A) 1/10 \n\n B) 1/1000 \n\n C) 1/10000",
        "According to the WHO, how long does immunity to Covid 19 persist after vaccination? \n\n A) for one year \n\n B) it is unknown \n\n C) about half a year",
        "According to annual CDC research, wearing masks for 100 days reduces the spread of covid 19 virus? \n\n A) does not reduces spread \n\n B) reduces spread by about 20% \n\n C) reduces spread by about 1%"
    )
    var rightAnswers = listOf(1, 2, 3, 3, 2, 3)

    fun showToast(answer: Int, textView: TextView) {
        if (questionNo > 4) {
            textView.setText("You WIN !!!")
        } else {
            if (answer == rightAnswers.get(questionNo)) {
                context?.toast("Good !!")
                questionNo = questionNo + 1
                textView.setText(questions.get(questionNo))
            } else {
                context?.toast("WRONG !!")
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreate(savedInstanceState)
        notificationsViewModel =
            ViewModelProvider(this).get(NotificationsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_notifications, container, false)
        val buttonA: Button = root.findViewById(R.id.buttonA)
        val buttonB: Button = root.findViewById(R.id.buttonB)
        val buttonC: Button = root.findViewById(R.id.buttonC)
        val questionsText: TextView = root.findViewById(R.id.quizQuestions)
        notificationsViewModel.text.observe(viewLifecycleOwner, Observer {

            buttonA.setOnClickListener {
                showToast(1, questionsText)
            }

            buttonB.setOnClickListener {
                showToast(2, questionsText)
            }

            buttonC.setOnClickListener {
                showToast(3, questionsText)
            }

        })
        return root
    }

}