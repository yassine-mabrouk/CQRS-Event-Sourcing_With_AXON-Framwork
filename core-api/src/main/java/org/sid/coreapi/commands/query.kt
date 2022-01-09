package org.sid.coreapi.commands

abstract class BaseEvent<T> (
        open val id :T
)
data class CreatedCustomerEvent(
        override val id :String,
        val name :String,
        val email :String
) : BaseEvent<String>(id);

data class UpdatedCustomerEvent(
        override val id :String,
        val name :String,
        val email :String
) : BaseEvent<String>(id);
data class DeletedCustomerEvent(
        override val id :String,
) : BaseEvent<String>(id);

