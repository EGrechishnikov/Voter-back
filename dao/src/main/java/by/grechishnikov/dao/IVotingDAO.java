package by.grechishnikov.dao;

import by.grechishnikov.entity.Voting;

import java.util.List;

public interface IVotingDAO extends IBaseDAO<Voting> {
    List<Voting> getAll(int start, int count);
}
