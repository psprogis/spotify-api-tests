Feature: As an existing user I am able to create and update playlists

  Scenario Outline: New playlist with <name> can be successfully added
    Given get current size of playlists for the current user
    When I create new playlist with name <name> for the current user
    Then current size of playlists increased
    Examples:
      | name          |
      | any           |
      | TwoTracksPlay |

  Scenario: Playlist can be renamed successfully
    Given I choose any predefined playlist
    When update name of playlist to changedTest
    Then make sure that updates applied successfully: name updated to changedTest

  Scenario: New playlist can be updated successfully
    Given I create new playlist with name NewPlayList for the current user
    When update description of playlist to changedDescription
    Then make sure that updates applied successfully: description updated to changedDescription