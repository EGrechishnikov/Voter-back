package by.grechishnikov.service;

import by.grechishnikov.dto.ChosenVariantDTO;
import by.grechishnikov.entity.Voting;

import java.util.List;

public interface IVotingService extends IBaseService<Voting> {
    void createVoting(String json, byte[] bytes, String fileName);

    List<Voting> getAll(int page);

    List<ChosenVariantDTO> getAllVotesForVoting(int votingId);
}
