package xyz.czanik.carapp.carbrands

import androidx.fragment.app.Fragment
import xyz.czanik.carapp.Container

abstract class BaseFragment : Fragment() {

    protected val container: Container get() = requireActivity().application as Container
}