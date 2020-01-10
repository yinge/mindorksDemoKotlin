package com.seven.mindorks.data.model.others

import com.seven.mindorks.data.model.db.Option
import com.seven.mindorks.data.model.db.Question

/**
 * at 2019/12/4
 * at 10:37
 * summary:
 */
class QuestionCardData(question: Question, options: List<Option>) {
    var mShowCorrectOptions = false

    var options: List<Option> = options

    var question: Question = question
}