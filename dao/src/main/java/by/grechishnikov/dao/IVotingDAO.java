package by.grechishnikov.dao;

import by.grechishnikov.entity.Voting;

import java.util.List;

/**
 * DAO for Voting entity
 */
public interface IVotingDAO extends IBaseDAO<Voting> {
    /**
     * Get all Votings for pagination
     *
     * @param start - from
     * @param count - count of beans
     * @return - list
     */
    List<Voting> getAll(int start, int count);


    /**
     * Get count of all voting records
     * @return - count
     */
    long getCountOfAllVotings();
}
