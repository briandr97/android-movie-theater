package woowacourse.movie.reservation

import woowacourse.movie.PaymentType
import woowacourse.movie.bookingHistory.BookingDatabase
import woowacourse.movie.bookingHistory.BookingEntity
import woowacourse.movie.bookingHistory.SeatEntity
import woowacourse.movie.ticket.Position
import woowacourse.movie.ticket.Seat
import woowacourse.movie.ticket.SeatRank
import woowacourse.movie.ticket.Ticket

object ReservationRepository {
    fun getReservations(): List<Reservation> {
        return BookingDatabase.bookings.map { it.toReservation() }
    }

    fun getReservation(id: Long): Reservation {
        return BookingDatabase.selectBooking(id).toReservation()
    }

    private fun Reservation.toBookingEntity(): BookingEntity {
        return BookingEntity(
            id = BookingDatabase.getNewId(),
            movieId = this.movieId,
            bookedDateTime = this.bookedDateTime,
            paymentType = this.paymentType.ordinal,
            seats = this.tickets.map { it.seat.toSeatEntity() },
        )
    }

    private fun BookingEntity.toReservation(): Reservation {
        return Reservation(
            tickets = this.seats.map {
                Ticket(this.movieId, this.bookedDateTime, it.toSeat())
            }.toSet(),
            paymentType = PaymentType.find(this.paymentType),
        )
    }

    private fun SeatEntity.toSeat(): Seat {
        return Seat(
            SeatRank.find(this.rank),
            Position(this.row, this.column),
        )
    }

    private fun Seat.toSeatEntity(): SeatEntity {
        return SeatEntity(
            rank = this.rank.ordinal,
            row = this.position.row,
            column = this.position.column,
        )
    }
}
