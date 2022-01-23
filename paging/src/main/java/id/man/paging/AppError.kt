package id.man.paging

/**
 *
 * Created by Lukmanul Hakim on  1/14/2022
 * devs.lukman@gmail.com
 */
class AppError(val code: Int, val error: String?) : Exception(error) {
}