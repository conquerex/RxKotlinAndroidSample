package what.the.rxkotlin.android.apis.response

import what.the.rxkotlin.android.data.Support

/**
 * Created by jongkook on 2021.01.04
 * .
 * support는 프로젝트의 모든 api에 일관성을 갖고 사용되기 때문에
 * 모든 api의 기본 클래스를 만든 다음 필요에 따라 각 api의 클래스를 확장하는 것이 좋다
 */
open class BaseApiResponse {
    val support: Support? = null
}