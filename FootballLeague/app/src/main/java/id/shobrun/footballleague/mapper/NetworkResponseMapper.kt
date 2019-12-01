package id.shobrun.footballleague.mapper

import id.shobrun.footballleague.models.NetworkResponseModel

interface NetworkResponseMapper<in FROM : NetworkResponseModel>{
    fun onLastPage(response : FROM) : Boolean
}

