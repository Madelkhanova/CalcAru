package com.example.calcaruzhan

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import net.objecthunter.exp4j.ExpressionBuilder
import kotlin.math.abs

/**
 * A simple [Fragment] subclass.
 */
class CalcFragment : Fragment() {
    private var tvExpression: TextView? = null
    private var tvClear: Button? = null
    private var tvOne: Button? = null
    private var tvTwo: Button? = null
    private var tvThree: Button? = null
    private var tvFour: Button? = null
    private var tvFive: Button? = null
    private var tvSix: Button? = null
    private var tvSeven: Button? = null
    private var tvEight: Button? = null
    private var tvNine: Button? = null
    private var tvZero: Button? = null
    private var tvMul: Button? = null
    private var tvDivision: Button? = null
    private var tvPlus: Button? = null
    private var tvMinus: Button? = null
    private var tvDot: Button? = null
    private var tvResult: TextView? = null
    private var tvEq: TextView? = null
    private var tvNegat: Button? = null
    private var tvPower: Button? = null
    private var tvSquare: Button? = null
    private var tvTripl: Button? = null
    private var tvFact: Button? = null
    private var tvSqrt: Button? = null
    private var tvSin: Button? = null
    private var tvCos: Button? = null
    private var tvTan: Button? = null
    private var tvCot: Button? = null
    private var rootView: View? = null


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("expression", tvExpression?.text.toString())
        outState.putString("result", tvResult?.text.toString())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(R.layout.fragment_calc, null)
        bindViews(savedInstanceState)
        return rootView
    }

    private fun bindViews(
        savedInstanceState: Bundle?
    ) {

        tvExpression = (rootView as ViewGroup).findViewById(R.id.tvExpression)
        tvResult = (rootView as ViewGroup).findViewById(R.id.tvResult)
        if (savedInstanceState != null) {
            tvExpression?.text = savedInstanceState.getString("expression")
            tvResult?.text = savedInstanceState.getString("result")
        }
        tvClear = (rootView as ViewGroup).findViewById(R.id.tvClear)
        tvOne = (rootView as ViewGroup).findViewById(R.id.tvOne)
        tvTwo = (rootView as ViewGroup).findViewById(R.id.tvTwo)
        tvThree = (rootView as ViewGroup).findViewById(R.id.tvThree)
        tvFour = (rootView as ViewGroup).findViewById(R.id.tvFour)
        tvFive = (rootView as ViewGroup).findViewById(R.id.tvFive)
        tvSix = (rootView as ViewGroup).findViewById(R.id.tvSix)
        tvSeven = (rootView as ViewGroup).findViewById(R.id.tvSeven)
        tvEight = (rootView as ViewGroup).findViewById(R.id.tvEighnt)
        tvNine = (rootView as ViewGroup).findViewById(R.id.tvNine)
        tvZero = (rootView as ViewGroup).findViewById(R.id.tvZero)
        tvMul = (rootView as ViewGroup).findViewById(R.id.tvMul)
        tvDivision = (rootView as ViewGroup).findViewById(R.id.tvDivision)
        tvPlus = (rootView as ViewGroup).findViewById(R.id.tvPlus)
        tvMinus = (rootView as ViewGroup).findViewById(R.id.tvMinus)
        tvDot = (rootView as ViewGroup).findViewById(R.id.tvDot)
        tvEq = (rootView as ViewGroup).findViewById(R.id.tvEqual)
        tvPower = (rootView as ViewGroup).findViewById(R.id.tvPower)
        tvNegat = (rootView as ViewGroup).findViewById(R.id.tvNegat)
        tvSquare = (rootView as ViewGroup).findViewById(R.id.tvsquare)
        tvTripl = (rootView as ViewGroup).findViewById(R.id.tvtripl)
        tvFact = (rootView as ViewGroup).findViewById(R.id.tvfact)
        tvSqrt = (rootView as ViewGroup).findViewById(R.id.tvsqrt)
        tvSin = (rootView as ViewGroup).findViewById(R.id.tvSin)
        tvCos = (rootView as ViewGroup).findViewById(R.id.tvCos)
        tvTan = (rootView as ViewGroup).findViewById(R.id.tvTan)
        tvCot = (rootView as ViewGroup).findViewById(R.id.tvCot)
        //Numbers

        tvOne?.setOnClickListener { appendOnExpression(string = "1") }
        tvTwo?.setOnClickListener { appendOnExpression(string = "2") }
        tvThree?.setOnClickListener { appendOnExpression(string = "3") }
        tvFour?.setOnClickListener { appendOnExpression(string = "4") }
        tvFive?.setOnClickListener { appendOnExpression(string = "5") }
        tvSix?.setOnClickListener { appendOnExpression(string = "6") }
        tvSeven?.setOnClickListener { appendOnExpression(string = "7") }
        tvEight?.setOnClickListener { appendOnExpression(string = "8") }
        tvNine?.setOnClickListener { appendOnExpression(string = "9") }
        tvZero?.setOnClickListener { appendOnExpression(string = "0") }
        tvDot?.setOnClickListener { appendOnExpression(string = ".") }

        //Operations
        tvPlus?.setOnClickListener { appendOnExpression(string = "+") }
        tvMinus?.setOnClickListener { appendOnExpression(string = "-") }
        tvMul?.setOnClickListener { appendOnExpression(string = "*") }
        tvDivision?.setOnClickListener { appendOnExpression(string = "/") }
        tvPower?.setOnClickListener { appendOnExpression(string = "^") }
        tvSquare?.setOnClickListener { appendOnExpression(string = "^2") }
        tvTripl?.setOnClickListener { appendOnExpression(string = "^3") }
        tvFact?.setOnClickListener { factorial() }
        tvSqrt?.setOnClickListener { appendOnExpression(string = "√") }
        tvSin?.setOnClickListener { appendOnExpression(string = "sin") }
        tvCos?.setOnClickListener { appendOnExpression(string = "cos") }
        tvTan?.setOnClickListener { appendOnExpression(string = "tan") }
        tvCot?.setOnClickListener { appendOnExpression(string = "cot") }
        tvNegat?.setOnClickListener {
            if (tvExpression?.text!!.contains("+") || tvExpression?.text!!.contains("-")
                || tvExpression?.text!!.contains("*") || tvExpression?.text!!.contains("/")
            ) {
                try {
                    val ex = ExpressionBuilder(tvExpression?.text.toString()).build()
                    val res = ex.evaluate()
                    val longRes = res.toLong()
                    if (res == longRes.toDouble())
                        tvResult?.text = (longRes * (-1)).toString()
                    else
                        tvResult?.text = (res * (-1)).toString()
                } catch (e: Exception) {
                }
            } else
                tvResult?.text = "-" + tvExpression?.text
        }
        tvClear?.setOnClickListener {
            tvExpression?.text = ""
            tvResult?.text = ""
        }
        tvEq?.setOnClickListener {
            equalFunc()
        }

    }

    private fun appendOnExpression(string: String) {

        if (tvResult?.text != "") {
            tvExpression?.text = tvResult?.text
            tvResult?.text = ""
        }
        if (string === "√") {
            tvExpression?.text = "sqrt(" + tvExpression?.text + ")"
        } else if (string === "cos") {
            tvExpression?.text = "cos(" + tvExpression?.text + ")"
        } else if (string === "sin") {
            tvExpression?.text = "sin(" + tvExpression?.text + ")"
        } else if (string === "tan") {
            tvExpression?.text = "tan(" + tvExpression?.text + ")"
        } else if (string === "cot") {
            tvExpression?.text = "1/tan(" + tvExpression?.text + ")"
        } else
            tvExpression?.append(string)
    }

    private fun equalFunc() {
        try {
            val ex = ExpressionBuilder(tvExpression?.text.toString()).build()
            val res = ex.evaluate()
            val longRes = res.toLong()
            if (res == longRes.toDouble())
                tvResult?.text = longRes.toString()
            else
                tvResult?.text = res.toString()
        } catch (e: Exception) {
        }
    }

    private fun factorial() {
        var s: Long
        try {
            s = 1
            var i = 1
            while (i <= abs(tvExpression?.text.toString().toDouble())) {
                s *= i.toLong()
                i++
            }
            tvResult?.text = s.toString()
        } catch (e: NumberFormatException) {

        }
    }
}


