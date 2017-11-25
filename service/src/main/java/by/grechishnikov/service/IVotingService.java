package by.grechishnikov.service;

import by.grechishnikov.dto.ChosenVariantDTO;
import by.grechishnikov.dto.VotingsDTO;
import by.grechishnikov.entity.Voting;

import java.util.List;

/**
 * Service for Voting entity
 */
public interface IVotingService extends IBaseService<Voting> {
    /**
     * Create voting
     *
     * @param json     - JSON with DTO object
     * @param bytes    - image
     * @param fileName - image name
     */
    void createVoting(String json, byte[] bytes, String fileName);

    /**
     * Get voting list from DB with the count of all voting records
     *
     * @param page - number of page
     * @return - dto that includes a list and a records count
     */
    VotingsDTO getAll(int page);

    /**
     * Get all votes for voting
     *
     * @param votingId - id in DB
     * @return - list
     */
    List<ChosenVariantDTO> getAllVotesForVoting(int votingId);
}
