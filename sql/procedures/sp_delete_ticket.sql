SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[sp_delete_ticket]
(
	@USER_ID INT
)
AS
BEGIN
    
    SET NOCOUNT ON

	DELETE
	FROM ticket
	WHERE id_usuario = @USER_ID

END
GO