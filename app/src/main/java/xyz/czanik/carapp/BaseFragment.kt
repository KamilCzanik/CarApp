package xyz.czanik.carapp

import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment() {

    protected val container: Container get() = requireActivity().application as Container
}