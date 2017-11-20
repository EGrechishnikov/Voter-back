package by.grechishnikov.dao;

import by.grechishnikov.dto.MyVoteDTO;
import by.grechishnikov.entity.Vote;

import java.util.List;

/**
 * DAO for Vote entity
 */
public interface IVoteDAO extends IBaseDAO<Vote> {
    /**
     * Get list of user votes
     *
     * @param userId - user id in DB
     * @return - list
     */
    List<MyVoteDTO> getAllVotesForUser(int userId);
}
