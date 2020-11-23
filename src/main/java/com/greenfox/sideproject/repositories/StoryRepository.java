package com.greenfox.sideproject.repositories;

import com.greenfox.sideproject.models.Story;
import com.greenfox.sideproject.models.UserCharacter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StoryRepository extends JpaRepository<Story, Long> {

    @Query("select s.id from Story s where s.tale.id=:tale_id and s.user.id=:user_id")
    Long findIdByTaleIdAndUserId(@Param("tale_id") Long id, @Param("user_id") Long userId);

    @Query("select s from Story s where s.tale.id=:tale_id and s.user.id=:user_id")
    Story findByTaleIdAndUserId(@Param("tale_id") Long id, @Param("user_id") Long userId);

    @Query("select s.userCharacter from Story s where s.id=:story_id")
    UserCharacter findCharacter(@Param("story_id") Long storyId);
}
