package woowacourse.app.presentation.util

import android.content.Context
import woowacourse.app.data.advertisement.AdvertisementDao
import woowacourse.app.data.advertisement.AdvertisementRepositoryImpl
import woowacourse.app.data.movie.MovieDao
import woowacourse.app.data.movie.MovieRepositoryImpl
import woowacourse.app.data.theater.MovieTimeDao
import woowacourse.app.data.theater.TheaterDao
import woowacourse.app.data.theater.TheaterRepositoryImpl
import woowacourse.domain.advertisement.AdvertisementRepository
import woowacourse.domain.movie.MovieRepository
import woowacourse.domain.movie.ScreeningPeriod
import woowacourse.domain.theater.ScreeningMovie
import woowacourse.domain.theater.SeatStructure
import woowacourse.domain.theater.TheaterRepository
import java.time.LocalDate
import java.time.LocalTime

fun initAdvertisement(context: Context) {
    val advertisementRepo: AdvertisementRepository =
        AdvertisementRepositoryImpl(AdvertisementDao(context))
    advertisementRepo.addAdvertisement("https://woowacourse.github.io/")
}

fun initMovie(context: Context) {
    val movieRepo = MovieRepositoryImpl(MovieDao(context))
    movieRepo.addMovie(
        title = "해리 포터와 마법사의 돌",
        screeningPeriod = ScreeningPeriod(
            LocalDate.of(2023, 4, 26),
            LocalDate.of(2023, 4, 30),
        ),
        runningTime = 152,
        "《해리 포터와 마법사의 돌》은 2001년 J. K. 롤링의 동명 소설을 원작으로 하여 만든, 영국과 미국 합작, 판타지 영화이다. 해리포터 시리즈 영화 8부작 중 첫 번째에 해당하는 작품이다. 크리스 콜럼버스가 감독을 맡았다.",
    )

    movieRepo.addMovie(
        title = "아이언 맨",
        screeningPeriod = ScreeningPeriod(
            LocalDate.of(2024, 4, 10),
            LocalDate.of(2024, 4, 20),
        ),
        runningTime = 125,
        "천재적인 두뇌와 재능으로 세계 최강의 무기업체를 이끄는 CEO이자, 타고난 매력으로 셀러브리티 못지않은 화려한 삶을 살아가던 억만장자 토니 스타크. 아프가니스탄에서 자신이 개발한 신무기 발표를 성공리에 마치고 돌아가던 그는 게릴라군의 갑작스런 공격에 의해 가슴에 치명적인 부상을 입고 게릴라군에게 납치된다. 가까스로 목숨을 건진 그에게 게릴라군은 자신들을 위한 강력한 무기를 개발하라며 그를 위협한다. 그러나 그는 게릴라군을 위한 무기 대신, 탈출을 위한 무기가 장착된 철갑수트를 몰래 만드는 데 성공하고, 그의 첫 수트인 ‘Mark1’를 입고 탈출에 성공한다.\n" +
            "미국으로 돌아온 토니 스타크는 자신이 만든 무기가 많은 사람들의 생명을 위협하고, 세상을 엄청난 위험에 몰아넣고 있다는 사실을 깨닫고 무기사업에서 손 뗄 것을 선언한다. 그리고, Mark1을 토대로 최강의 하이테크 수트를 개발하는 데 자신의 천재적인 재능과 노력을 쏟아 붓기 시작한다. 탈출하는 당시 부서져버린 Mark1를 바탕으로 보다 업그레이드 된 수트 Mark2를 만들어낸 토니 스타크. 거기에 만족하지 않고, 숱한 시행착오와 실패 끝에 자신의 모든 능력과 현실에서 가능한 최강의 최첨단 과학 기술이 집적된 하이테크 수트 Mark3를 마침내 완성, 최강의 슈퍼히어로 ‘아이언맨’으로 거듭난다.\n" +
            "토니 스타크가 탈출하는 과정을 통해 Mark1의 가공할 위력을 확인한 게릴라 군은 토니 스타크가 미처 회수하지 못한 Mark1의 잔해와 설계도를 찾아낸다. Mark1을 재조립하여 그들의 목적을 이루기 위한 거대하고 강력한 철갑수트를 제작하려는 음모를 꾸미는 게릴라군. 토니 스타크가 갖고 있던 에너지원을 훔쳐 ‘아이언맨’을 능가하는 거대하고 강력한 ‘아이언 몽거’를 완성한 그들은 세계 평화를 위협하고, 토니 스타크는 그들의 음모과 배후세력이 누구인지를 알게 되는데...!",
    )

    movieRepo.addMovie(
        title = "스즈메의 문단속",
        screeningPeriod = ScreeningPeriod(
            LocalDate.of(2024, 3, 21),
            LocalDate.of(2024, 4, 11),
        ),
        runningTime = 122,
        "몽환적인 밤하늘 아래, 펼쳐진 초원 속의 폐허를 숨가쁘게 돌아다니며 어머니를 찾는 어린 시절의 스즈메를 보여 주면서 영화가 시작된다. 한창을 어머니를 찾다 지친 어린 스즈메는 자리에 주저 앉고, 그런 스즈메 앞에 한 여성이 이름을 부르며 다가오자 그 순간 스즈메는 잠에서 깬다.",
    )
}

fun initTheater(context: Context) {
    val theaterRepo: TheaterRepository = TheaterRepositoryImpl(
        TheaterDao(context),
        MovieTimeDao(context),
        MovieDao(context),
    )

    val movieRepo: MovieRepository = MovieRepositoryImpl(MovieDao(context))

    theaterRepo.addTheater(
        theaterName = "선릉",
        screeningMovies = listOf(
            ScreeningMovie(
                movieRepo.getMovie(1)!!,
                listOf(LocalTime.of(10, 0), LocalTime.of(16, 0), LocalTime.of(20, 0)),
            ),
        ),
        seatStructure = SeatStructure(
            rowSize = 5,
            columnSize = 4,
            sRankRange = listOf(2..3),
            aRankRange = listOf(4..4),
            bRankRange = listOf(0..1),
        ),
    )

    theaterRepo.addTheater(
        theaterName = "잠실",
        screeningMovies = listOf(
            ScreeningMovie(
                movieRepo.getMovie(1)!!,
                listOf(
                    LocalTime.of(9, 0),
                    LocalTime.of(13, 0),
                    LocalTime.of(17, 0),
                    LocalTime.of(21, 0),
                ),
            ),
            ScreeningMovie(
                movieRepo.getMovie(2)!!,
                listOf(
                    LocalTime.of(9, 0),
                    LocalTime.of(13, 0),
                    LocalTime.of(17, 0),
                    LocalTime.of(21, 0),
                ),
            ),
            ScreeningMovie(
                movieRepo.getMovie(3)!!,
                listOf(
                    LocalTime.of(9, 0),
                    LocalTime.of(13, 0),
                    LocalTime.of(17, 0),
                    LocalTime.of(21, 0),
                ),
            ),
        ),
        seatStructure = SeatStructure(
            rowSize = 7,
            columnSize = 5,
            sRankRange = listOf(3..4),
            aRankRange = listOf((4..6), (2..2)),
            bRankRange = listOf(0..1),
        ),
    )

    theaterRepo.addTheater(
        theaterName = "신림",
        screeningMovies = listOf(
            ScreeningMovie(
                movieRepo.getMovie(2)!!,
                listOf(LocalTime.of(12, 0), LocalTime.of(14, 0), LocalTime.of(16, 0)),
            ),
            ScreeningMovie(
                movieRepo.getMovie(3)!!,
                listOf(LocalTime.of(12, 0), LocalTime.of(14, 0), LocalTime.of(16, 0)),
            ),
        ),
        seatStructure = SeatStructure(
            rowSize = 6,
            columnSize = 5,
            sRankRange = listOf(3..4),
            aRankRange = listOf(5..5),
            bRankRange = listOf(0..2),
        ),
    )
}
