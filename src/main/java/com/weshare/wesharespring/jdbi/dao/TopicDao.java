package com.weshare.wesharespring.jdbi.dao;


import com.weshare.wesharespring.entity.Topic;
import com.weshare.wesharespring.jdbi.mapper.TopicMapper;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.GetGeneratedKeys;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import java.util.List;

@RegisterMapper(TopicMapper.class)
public abstract class TopicDao {


    @SqlQuery("SELECT * FROM topic WHERE status = :status")
    public abstract List<Topic> getAll(
        @Bind("status") final Integer status);

    @SqlQuery("SELECT * FROM topic WHERE topic_id = :topic_id")
    public abstract Topic getById(
        @Bind("topic_id") final Long topicId);

    @SqlUpdate(
        "INSERT INTO topic (user_id, topic_title, topic_detail, topic_target, topic_length, topic_type, status, time_created, time_updated) " +
            "VALUES (:user_id, :topic_title, :topic_detail, :topic_target, :topic_length, :topic_type, :status, :time_now, :time_now)")
    @GetGeneratedKeys
    public abstract Long create(
        @Bind("user_id") final Long userId,
        @Bind("topic_title") final String topicTitle,
        @Bind("topic_detail") final String topicDetail,
        @Bind("topic_target") final String topicTarget,
        @Bind("topic_length") final Integer topicLength,
        @Bind("topic_type") final Integer topicType,
        @Bind("status") final Integer status,
        @Bind("time_now") final Long timeNow);

    @SqlUpdate(
        "UPDATE topic SET topic_title = :topic_title, topic_detail = :topic_detail, topic_target = :topic_target, " +
            "topic_length = :topic_length, topic_type = :topic_type, time_updated = :time_updated " +
            "WHERE topic_id = :topic_id")
    public abstract int update(
        @Bind("topic_id") final Long topicId,
        @Bind("topic_title") final String topicTitle,
        @Bind("topic_detail") final String topicDetail,
        @Bind("topic_target") final String topicTarget,
        @Bind("topic_length") final Integer topicLength,
        @Bind("topic_type") final Integer topicType,
        @Bind("time_updated") final Long timeUpdated);

    @SqlUpdate("UPDATE topic SET status = 0 WHERE topic_id = :topic_id")
    public abstract int delete(
        @Bind("topic_id") final Long topicId);

    abstract void close();
}
