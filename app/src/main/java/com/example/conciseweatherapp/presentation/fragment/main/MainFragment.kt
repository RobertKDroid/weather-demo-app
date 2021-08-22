package com.example.conciseweatherapp.presentation.fragment.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.transition.TransitionManager
import com.example.conciseweatherapp.data.model.DailyWeather
import com.example.conciseweatherapp.domain.model.WeatherData
import com.example.conciseweatherapp.utils.toCelsius
import com.futureprocessing.weatherapp.R
import com.futureprocessing.weatherapp.databinding.FragmentMainBinding
import com.xwray.groupie.GroupieAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding
    private val adapter = GroupieAdapter()

    private val viewModel: MainFragmentViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUi()
        setupViewModel()
    }

    private fun setupUi() {
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun setupViewModel() {
        viewModel.singleLiveEvent.observe(viewLifecycleOwner, {
            when (it) {
                is MainFragmentEvent.WeatherFetchError -> renderError(it)
            }
        })
        viewModel.stateLiveData.observe(viewLifecycleOwner, {
            when (it) {
                is MainFragmentState.WeatherFetched -> renderWeatherFetched(it)
            }
        })
    }

    private fun renderError(state: MainFragmentEvent.WeatherFetchError) {
        AlertDialog.Builder(requireContext())
            .setTitle(getString(R.string.error))
            .setMessage(
                getString(
                    R.string.fetching_weather_failed_do_you_want_to_try_again,
                    state.message
                )
            )
            .setPositiveButton(getString(R.string.yes)) { dialog, _ ->
                viewModel.getWeatherData()
                dialog?.dismiss()
            }
            .setNegativeButton(getString(R.string.no)) { dialog, _ ->
                dialog?.dismiss()
            }
            .create()
            .show()
    }

    private fun renderWeatherFetched(state: MainFragmentState.WeatherFetched) {
        TransitionManager.beginDelayedTransition(binding.root)
        with(state.weatherResponse) {
            binding.progressBar?.isVisible = false
            binding.cityHeaderTextView.text = getString(
                R.string.date_temperature_values,
                city.name,
                averageTemperature.toCelsius()
            )
            if (adapter.itemCount == 0)
                adapter.updateAsync(viewModel.getWeatherListItems(dailyWeatherList) {
                    onDailyWeatherItemClick(this, it)
                })
        }
    }

    private fun onDailyWeatherItemClick(weatherData: WeatherData, dailyWeather: DailyWeather) {
        findNavController().navigate(
            MainFragmentDirections.actionMainFragmentToWeatherDetailsFragment(
                dailyWeather, weatherData
            )
        )
    }
}
